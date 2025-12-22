package demo.controller;


import demo.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
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
    public ResponseEntity<List<demo.Model.Post>> login (){
        var listPost = loginService.answerPost();
        return ResponseEntity.ok(listPost);
    }



    @GetMapping("/getPostAsync")
    public CompletableFuture<List<demo.Model.Post>> answerPost() {
        return  loginService.answerPostAsync();
    }
}
