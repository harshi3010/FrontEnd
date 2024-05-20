package com.love2code.springcoredemo.config;

import com.love2code.springcoredemo.common.Coach;
import com.love2code.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig{

    //@Bean use case - Take existing third-party class and expose as a spring bean
    @Bean("aquatic")
    public Coach swimCoach(){
        return new SwimCoach();  //the bean id defaults to the method name
    }
}
