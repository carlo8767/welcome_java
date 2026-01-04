package langChain4J;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.ollama.OllamaChatModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AssesSwitchLang4J {


    public static String apiKey = System.getenv("OLLAMA_API_KEY");


    public static String sendToLLM (List<ChatMessage> chatMessages, String vendors) throws Exception {

      ChatModel chatModel =   switch (vendors){
           case "minimax-m2.1","gemini-3-flash-preview:cloud" , "nemotron-3-nano:30b-cloud" ->
                     OllamaChatModel.builder()
                    .baseUrl("https://ollama.com")
                    .modelName(vendors)
                    .customHeaders(Map.of(
                            "Authorization", "Bearer " + apiKey
                    ))
                    .temperature(.3)
                    .numPredict(600)
                    .build();

          default -> throw new Exception("Unkown LLM" + vendors);
        };
        ChatResponse chatResponse = chatModel.chat(chatMessages);
        return  chatResponse.aiMessage().text();

    }



    static void main (String [] args) throws Exception {

        List<ChatMessage> chatMessageList = new ArrayList<>();
        UserMessage userMessage = new UserMessage("How simple to find a work as Java Software Developer?");
        chatMessageList.add(userMessage);
        var first = AssesSwitchLang4J.sendToLLM(chatMessageList, "minimax-m2.1");
        var second = AssesSwitchLang4J.sendToLLM(chatMessageList, "gemini-3-flash-preview:cloud");
        System.out.println(first);
        System.out.println(second);
        String p = STR."evaluate these LLM model for accuracy, precions, and comprenshion from one to ten :  \{first} \{second}";
        chatMessageList.clear();
        chatMessageList.add(UserMessage.from(p));
        var hello = AssesSwitchLang4J.sendToLLM(chatMessageList, "nemotron-3-nano:30b-cloud");
        System.out.println(hello);





    }

}
