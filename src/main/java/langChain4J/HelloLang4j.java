package langChain4J;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;


import java.util.Map;

public class HelloLang4j {




    static void main (String [] args){


        String apiKey = System.getenv("OLLAMA_API_KEY");
        ChatModel model = OllamaChatModel.builder()
                .baseUrl("https://ollama.com")
                .modelName("minimax-m2.1")
                .customHeaders(Map.of(
                        "Authorization", "Bearer " + apiKey
                ))
                .build();
        String answer = model.chat("tell me how are you ?");
        System.out.println(answer);

    }

}
