package langChain4J;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HelloLang4j_Parameter {




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

        SystemMessage systemMessage = new SystemMessage("You are a angry guys annoying to answer at the same question"); // SET THE TONE

        UserMessage userMessage = new UserMessage("Why I should learn  ?");
        List<ChatMessage> listMessage = new ArrayList<>();
        listMessage.add(systemMessage);
        listMessage.add(userMessage);
        var answer = chatModel.chat(listMessage);
        System.out.println(answer.aiMessage().text());



    }

}
