package langChain4J;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HelloRag {



    public static void main (String []args){

        List<Document> documents = FileSystemDocumentLoader.loadDocuments("/home/robothg/Desktop/project_/project_Java/hello_java/welcome_java/src/main/java/langChain4J/documents");
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);


        String apyKey =  System.getenv("OLLAMA_API_KEY");

        ChatModel chatModel = OllamaChatModel.builder()
                .baseUrl("https://ollama.com")
                .modelName("nemotron-3-nano:30b-cloud")
                .customHeaders(Map.of(
                        "Authorization", "Bearer " + apyKey
                ))
                .temperature(.3)
                .numPredict(600)
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore))
                .build();



        System.out.println("WELCOME TO DEMO LANCASTER AI CHATBOT!!");

        while(true){
            Scanner scanner = new Scanner(System.in);
            var questionUser = scanner.nextLine();
            var answer = assistant.chat(questionUser);
            System.out.println(answer);
        }

    }
}
