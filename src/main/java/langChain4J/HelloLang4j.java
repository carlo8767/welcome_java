package langChain4J;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;



import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HelloLang4j {




    static void main (String [] args){



        String apiKey = System.getenv("OLLAMA_API_KEY");
        ChatModel chatModel = OllamaChatModel.builder()
                .baseUrl("https://ollama.com")
                .modelName("minimax-m2.1")
                .customHeaders(Map.of(
                        "Authorization", "Bearer " + apiKey
                ))
                .temperature(.3) // higher level higher creativity
                .build();



        while(true){
            Scanner scanner = new Scanner(System.in);
            var message = scanner.nextLine();
            UserMessage userMessage = UserMessage.from(message);
            var answer = chatModel.chat(userMessage);
            System.out.println(answer);

        }




    }

}
