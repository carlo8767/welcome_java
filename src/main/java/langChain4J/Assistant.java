package langChain4J;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {

    @SystemMessage("You are an experts student that using slang ")
    String chat(String userMessage);
}
