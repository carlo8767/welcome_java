package demo.Service;


import demo.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PostService {

    @Autowired
    RestTemplate restTemplate;

    public List<Post> answerPost(){
        Post  post = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1", Post.class);
        List<Post> listPost = new ArrayList<>();
        listPost.add(post);
        return listPost;
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<Post>> answerPostAsync() {
        try {
            // simulate blocking I/O call (e.g. external API)
            Thread.sleep(3000); // 3 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        List<Post> posts = List.of(
                new Post(1, 1, "title","body" ),
                new Post(2, 2, "title","body" )
        );

        return CompletableFuture.completedFuture(posts);
    }
}
