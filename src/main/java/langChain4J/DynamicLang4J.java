package langChain4J;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DynamicLang4J {




    static void main (String [] args){



        String apiKey = System.getenv("OLLAMA_API_KEY");
        ChatModel chatModel = OllamaChatModel.builder()
                .baseUrl("https://ollama.com")
                .modelName("minimax-m2.1")
                .customHeaders(Map.of(
                        "Authorization", "Bearer " + apiKey
                ))
                .temperature(.3)
                .numPredict(600)
                .build();

        Scanner s  = new Scanner(System.in);
        System.out.println("set up the personality");
        var answer = s.nextLine();
        SystemMessage systemMessage = new SystemMessage(answer);
        ArrayList<ChatMessage> chatMessages = new ArrayList<>();
        chatMessages.add(systemMessage);

        while(true){
            System.out.println("question");
            var question = s.nextLine();
            UserMessage userMessage = new UserMessage(question);
            chatMessages.add(userMessage);
            var listAnswer = chatModel.chat(chatMessages);
            System.out.println(listAnswer.aiMessage().text());
        }





    }

}
