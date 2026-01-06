package langChain4J;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HelloChatMemory {



    public static void main() {

        String apiKey = System.getenv("OLLAMA_API_KEY");
        List<ChatMessage> list = new ArrayList<>();
        SystemMessage systemMessage = SystemMessage.from("you are an expe computer science" +
                "lancster university Leipzig. The info are here " +
                ": https://www.lancasterleipzig.de/study/undergraduate/courses/computer-science-bsc/");

        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(100);
        ChatModel chatModel = OllamaChatModel.builder()
                .baseUrl("https://ollama.com")
                .modelName("nemotron-3-nano:30b-cloud")
                .customHeaders(Map.of(
                        "Authorization", "Bearer " + apiKey
                ))
                .temperature(.3)
                .numPredict(600)
                .build();

        while(true){
            Scanner scanner = new Scanner(System.in);
            var answer = scanner.nextLine();
            UserMessage userMessage = UserMessage.from(answer);
            chatMemory.add(userMessage);
            var answerAi = chatModel.chat(chatMemory.messages());
            var response = answerAi.aiMessage().text();
            System.out.println(response);
        }

    }
}
