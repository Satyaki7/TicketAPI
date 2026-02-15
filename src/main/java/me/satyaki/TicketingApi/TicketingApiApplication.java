package me.satyaki.TicketingApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TicketingApiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TicketingApiApplication.class, args);

		System.out.println("Application started successfully!");
	}

}
