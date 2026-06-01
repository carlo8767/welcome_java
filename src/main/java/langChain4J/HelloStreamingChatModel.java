package langChain4J;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.Capability;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.*;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;


import java.util.*;

import java.util.concurrent.*;


public class HelloStreamingChatModel {


    public static void answer ( StreamingChatModel streamingChatModel,  List<ChatMessage> listMessages ) throws InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        CountDownLatch latch = new CountDownLatch(1);
        streamingChatModel.chat(
                listMessages,
                new StreamingChatResponseHandler() {

                    @Override
                    public void onPartialResponse(String partialResponse) {
                        stringBuilder.append(partialResponse);
                        if(stringBuilder.length()>80){
                            System.out.println(stringBuilder.toString());
                            stringBuilder.delete(0, stringBuilder.length());
                        }
                    }
                    @Override
                    public void onCompleteResponse(ChatResponse completeResponse) {

                    }

                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                    }
                    }
        );


        latch.await(); // wait until complete

    }



    public static ChatResponse  completable ( StreamingChatModel streamingChatModel,  List<ChatMessage> listMessages ) {

        try {
            CompletableFuture<ChatResponse> futureResponse = new CompletableFuture<>();

            StringBuffer responseBuilder = new StringBuffer();
            streamingChatModel.chat(
                    listMessages,
                    new StreamingChatResponseHandler() {

                        @Override
                        public void onPartialResponse(String partialResponse) {
                            responseBuilder.append(partialResponse);
                        }
                        @Override
                        public void onCompleteResponse(ChatResponse completeResponse) {
                            futureResponse.complete(completeResponse);
                        }

                        @Override
                        public void onError(Throwable error) {
                            futureResponse.completeExceptionally(error);
                        }
                    }
            );
            return  futureResponse.get(60, TimeUnit.SECONDS);

        }

        catch (InterruptedException interruptedException){
            System.out.println(interruptedException);
        }
        catch (ExecutionException executionException){
            System.out.println(executionException);
        }
        catch (TimeoutException timeoutException){
            System.out.println(timeoutException);
        }

        return  null;
    }
    /*
    public ChatResponse get() {
        try {
            return futureResponse.get(60, SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }*/






    public static void main(String[] args) {

        String apyKey = "qRdUaIV8ew37UB9FnzzlNub_Ouc5zRCxmN6RyUfk65l3dIoaB4FMwnLP0ncIgbOs7vL4TqL3NcT3BlbkFJ3INRh-VaS9u_3FXVIEcqwGB-UC-H-12Xry7ps04bw1Ly_DXaW3aSxsKGbWcjsDa1sjI7qqQxwA";

        ChatModel chatModel = OpenAiChatModel.builder()
                .baseUrl("https://api.openai.com/v1")
                .modelName("gpt-4o")
                .customHeaders(Map.of(
                        "Authorization", "Bearer " + apyKey)
                )
                .temperature(0.3)
                .build();




        /*

        String apiKey = System.getenv("OLLAMA_API_KEY");
        List<ChatMessage> list = new ArrayList<>();
        SystemMessage systemMessage = SystemMessage.from("you are an expert in Java");
        ///  https://github.com/langchain4j/langchain4j/blob/main/langchain4j/src/test/java/dev/langchain4j/model/chat/response/StreamingChatResponseHandlerIT.java

        StreamingChatModel streamingChatModel = OllamaStreamingChatModel.builder()
                .baseUrl("https://ollama.com")
                .modelName("nemotron-3-nano:30b-cloud")
                .customHeaders(Map.of(
                        "Authorization", "Bearer " + apiKey
                ))
                .supportedCapabilities(Set.of(Capability.RESPONSE_FORMAT_JSON_SCHEMA))
                .temperature(.3)
                .numPredict(600)
                .build();
        StringBuilder answer = new StringBuilder();
        Scanner scannerAnswer = new Scanner(System.in);
        var answers = scannerAnswer.nextLine();
        UserMessage userMessage = UserMessage.from(answers);
        List<ChatMessage> listMessages = new ArrayList<>();
        listMessages.add(systemMessage);
        listMessages.add(userMessage);

        ChatResponse chatResponse = completable(streamingChatModel, listMessages);
        var answersFuture = chatResponse.aiMessage().text();
        System.out.println(answersFuture);

    */
    }

}

