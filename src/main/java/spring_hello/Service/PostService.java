package spring_hello.Service;


import org.springframework.beans.factory.annotation.Qualifier;
import spring_hello.Model.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class PostService {

    @Autowired
    RestTemplate restTemplate;

    @Qualifier("asyncExecution")
    ExecutorService executor;


    public List<PostModel> answerPost(){
        PostModel post = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1", PostModel.class);
        List<PostModel> listPost = new ArrayList<>();
        listPost.add(post);
        return listPost;
    }



    @Async("normalThread")
    public CompletableFuture<Integer> answerOSThread() {

        try {
            //simulate blocking I/O call (e.g. external API)
            var result = 0;
            for (int i = 0; i < 9; i++) {
                result += i * 2;
                Thread.sleep(200); //
                System.out.println(Thread.currentThread() + " " + Thread.currentThread().isVirtual());
            }
            return CompletableFuture.completedFuture(result);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

    }


    @Async("virtual")
    public CompletableFuture<Integer> answerVirtual() {

        try {
            // simulate blocking I/O call (e.g. external API)
            var result = 0;
            for (int i = 0; i < 9; i++) {
                result += i * 2;
                Thread.sleep(200); //
                System.out.println(Thread.currentThread() + " " + Thread.currentThread().isVirtual());
            }
            return CompletableFuture.completedFuture(result);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }



        @Async("normalThread") // CUSTOM THREAD POOL
    public CompletableFuture<List<PostModel>> normalThread() {
        try {
            // simulate blocking I/O call (e.g. external API)
            Thread.sleep(9); // 3 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        List<PostModel> posts = List.of(
                new PostModel(1, 1, "title","body" ),
                new PostModel(2, 2, "title","body" )
        );

        return CompletableFuture.completedFuture(posts);
    }






}
