package spring_hello.controller;


import spring_hello.Model.PostModel;
import spring_hello.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/login")
public class PostController {



    @Autowired
    PostService loginService;


    public PostController(){

    }

    @GetMapping("/getPost")
    public ResponseEntity<List<PostModel>> login (){
        var listPost = loginService.answerPost();
        return ResponseEntity.ok(listPost);
    }



    @GetMapping("/getPostAsync")
    public CompletableFuture<List<PostModel>> answerPost() {
        return  loginService.normalThread();
    }

    @GetMapping("/virtual")
    public CompletableFuture<Integer> answerVirtual() {
        var timeArrival = System.nanoTime();
         var a = loginService.answerVirtual();
         var timeElapesed = System.nanoTime() - timeArrival;

        return a.whenComplete((result ,error)-> {
            var timeElapsed =  System.nanoTime() - timeArrival;
            System.out.println("the time elasped is "+ timeElapesed);
        } );
    }

    @GetMapping("/normal")
    public CompletableFuture<Integer> normalThread() {
        return  loginService.answerOSThread();
    }














}
