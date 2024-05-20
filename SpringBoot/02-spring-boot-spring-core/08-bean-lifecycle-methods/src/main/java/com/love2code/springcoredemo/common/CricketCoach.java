package com.love2code.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component  //make available for dependency injection - mark class as Spring beans
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  //New object instance for each injection

public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In Constructor:" + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practise fast bowling for 15 minutes :-)";
    }
}
