package dev.harshada.runnerz.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //setup rest controller
public class FunRestController {
    // expose "/hello" that returns "Hello World"
    @GetMapping("/hello") //handle http GET request
    public String sayHello(){
        return "Hello World!";
    }

    //expose a new endpoint for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Run a hard 5k!";
    }

    //expose a another endpoint for gym freek people
    @GetMapping("/gympeople")
    public String gymPeople(){
        return "Gym People";
    }

}
