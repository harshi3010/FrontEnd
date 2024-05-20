package com.love2code.springcoredemo.common;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component  //make available for dependency injection - mark class as Spring beans
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In Constructor:" + getClass().getSimpleName());
    }

    //define our init method
    @PostConstruct
    public void doMyStartupStuff(){
            System.out.println("In doMyStartupStuff() : " +getClass().getSimpleName() );
    }
    //define our destroy method
    @PreDestroy
    public void doMyCleanupStuff(){
        System.out.println("In doMyCleanUpStuff() : " +getClass().getSimpleName() );
    }
    @Override
    public String getDailyWorkout() {
        return "Practise fast bowling for 15 minutes :-)";
    }
}
