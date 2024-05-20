package dev.harshada.runnerz.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //setup rest controller
public class FunRestController {
    // expose "/hello" that returns "Hello World"
    @GetMapping("/hello") //handle http GET request
    public String sayHello(){
        return "Hello World!";
    }
}
