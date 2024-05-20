package com.love2code.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController   //
public class DemoController {

    //define a private field for the dependency
    private Coach mycoach;

    //Create a constructor in your class for dependency injection - if only one constructor then @Autowired annotation is optional
    @Autowired       //tells Spring to inject a dependency
    public DemoController(Coach theCoach){
        mycoach = theCoach;
    }

    @GetMapping("/dailyworkout")    //rest endpoints is used
    public String getDailyWorkout(){
        return mycoach.getDailyWorkout();
    }
}
