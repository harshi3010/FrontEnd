package com.love2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component  //make available for dependency injection - mark class as Spring beans
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In Constructor:" + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practise fast bowling for 15 minutes :-)";
    }
}
