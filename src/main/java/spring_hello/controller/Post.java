package spring_hello.controller;


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
public class Post {



    @Autowired
    PostService loginService;


    public Post(){

    }

    @GetMapping("/getPost")
    public ResponseEntity<List<spring_hello.Model.Post>> login (){
        var listPost = loginService.answerPost();
        return ResponseEntity.ok(listPost);
    }



    @GetMapping("/getPostAsync")
    public CompletableFuture<List<spring_hello.Model.Post>> answerPost() {
        return  loginService.answerPostAsync();
    }

    @GetMapping("/virtual")
    public CompletableFuture<Integer> answerVirtual() {
        return  loginService.answerVirtual();
    }













}
