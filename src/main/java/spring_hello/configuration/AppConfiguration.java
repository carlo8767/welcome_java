package spring_hello.configuration;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@EnableAspectJAutoProxy
public class AppConfiguration {


    @Value("${OLLAMA_API_KEY}")
    private String name;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean (name="asyncExecution")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("asyncExecution");
        executor.initialize();
        return executor;
    }


    @Bean
    public ChatModel chatModelInit() {
        String apyKey = "OLLAMA_API_KEY";

        ChatModel chatModel = OllamaChatModel.builder()
                .baseUrl("https://ollama.com")
                .modelName("nemotron-3-nano:30b-cloud")
                .customHeaders(Map.of(
                        "Authorization", "Bearer " + apyKey
                ))
                .temperature(.3)
                .numPredict(600)
                .build();
        return  chatModel;
    }
}
