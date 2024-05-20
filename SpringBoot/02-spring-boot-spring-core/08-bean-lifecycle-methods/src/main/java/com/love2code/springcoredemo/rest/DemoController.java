package com.love2code.springcoredemo.rest;

import com.love2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController   //
public class DemoController {

    //define a private field for the dependency
    private Coach mycoach;
    private Coach anotherCoach;
    
    //Example of Setter Injection
  //1.Create a setter method(s) in your class for injection -- In Demo Controller
    //2. Configure the dependency injection with @Autowired Annotation

//    @Autowired
//    public void setMycoach(Coach theCoach) {
//		this.mycoach = theCoach;
//	}

    //Inject dependencies by calling ANY method on your class
    //Simple give : @Autowired
//    @Autowired
//    public void doSomeStuff(Coach theCoach) {
//    	System.out.println("Do Some Stuff..");
//    	mycoach = theCoach;
//    }
    
    //Example of Contructor Injection..
    //Create a constructor in your class for dependency injection - if only one constructor then @Autowired annotation is optional
//    @Autowired       //tells Spring to inject a dependency
//    public DemoController(Coach theCoach){
//        mycoach = theCoach;
//    }


    //All the dependencies injections for the bean will reference the SAME Bean
    @Autowired
    public DemoController(
            @Qualifier("cricketCoach") Coach theCoach,
            @Qualifier("cricketCoach") Coach theanotherCoach){  //both point to cricketCoach
        System.out.println("In Constructor:" + getClass().getName());
        mycoach = theCoach;
        anotherCoach = theanotherCoach;
    }

	@GetMapping("/dailyworkout")    //rest endpoints is used
    public String getDailyWorkout(){
        return mycoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public  String check(){
        return "Comparing Beans : myCoach == anotherCoach, " + (mycoach==anotherCoach);
        //It will give true or false depending on the bean Scope
        //Singleton : true
        //Prototype : false
    }
}
