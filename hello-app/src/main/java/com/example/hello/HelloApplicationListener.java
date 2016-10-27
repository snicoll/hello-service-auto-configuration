package com.example.hello;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

class HelloApplicationListener implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("=== Received " + event.getClass().getName());
	}

}
