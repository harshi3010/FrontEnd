package dev.harshada.runnerz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import dev.harshada.runnerz.rest.FunRestController;

@SpringBootApplication
public class RunnerzApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);
		FunRestController f1 = new FunRestController();
		f1.sayHello();
		}

}
