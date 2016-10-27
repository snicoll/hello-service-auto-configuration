package com.example.hello;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class HelloApp {

	public static void main(String[] args) {
		new SpringApplicationBuilder(HelloApp.class)
				.listeners(new HelloApplicationListener())
				.run(args);
	}

}
