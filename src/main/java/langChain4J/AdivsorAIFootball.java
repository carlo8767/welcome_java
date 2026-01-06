package langChain4J;

import dev.langchain4j.model.chat.ChatModel;

import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.ollama.OllamaChatModel;

import java.util.HashMap;
import java.util.Map;

public class AdivsorAIFootball {


    static void main (String [] args) throws Exception {
        String apiKey = System.getenv("OLLAMA_API_KEY");
        ChatModel chatModel = OllamaChatModel.builder()
                            .baseUrl("https://ollama.com")
                            .modelName("nemotron-3-nano:30b-cloud")
                            .customHeaders(Map.of(
                                    "Authorization", "Bearer " + apiKey
                            ))
                            .temperature(.3)
                            .numPredict(600)
                            .build();

        PromptTemplate promptTemplate = PromptTemplate.from("You are an advisor in football and you need to give " +
                                                        "me the prediction goal o no goal or over or not over on the football match {{footaballMatch}} and {{dateMatch}} base on linear regression model " +
                "with the last 10 match of the team ");
        Map<String, Object>  mapTemplate = new HashMap<>();
        mapTemplate.put("footaballMatch", "Egitto vs Benin");
        mapTemplate.put("dateMatch", "05.01.2026 17:00");
        Prompt prompt = promptTemplate.apply(mapTemplate);
        var response = chatModel.chat(prompt.text());
        System.out.println(response);





    }
}
