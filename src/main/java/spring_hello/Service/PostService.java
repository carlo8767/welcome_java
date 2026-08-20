package spring_hello.Service;


import spring_hello.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

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

    @Async("asyncExecution") // CUSTOM THREAD POOL
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



    @Async("virtual")
    public CompletableFuture<Integer> answerVirtual() {

        try {
            // simulate blocking I/O call (e.g. external API)
            var result = 0;
            for(int i=0; i<100000; i++) {
                result += i * 2;
                Thread.sleep(1000); // 3 seconds
                System.out.println(Thread.currentThread()+" "+Thread.currentThread().isVirtual());
            }
            return CompletableFuture.completedFuture(result);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }



    }
}
