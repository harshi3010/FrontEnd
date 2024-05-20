package com.love2code.springcoredemo.common;

import org.springframework.context.annotation.Bean;

public class SwimCoach implements Coach{

    //@Bean use case - Take existing third-party class and expose as a spring bean
    public SwimCoach() {
        System.out.println("In Constructor:" + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 10000 meters as a warm up";
    }
}
