package com.love2code.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.sound.midi.Track;

@Component
@Lazy
public class TrackCoach implements Coach
{
    public TrackCoach(){
        System.out.println("In Constructor:" + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Run hard 5k!";
    }
}
