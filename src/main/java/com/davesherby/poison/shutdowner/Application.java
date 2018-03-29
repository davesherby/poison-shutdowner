package com.davesherby.poison.shutdowner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {
	
	public static void main(String[] args) {
		System.setProperty("logback.configurationFile", "logback-standalone.xml");
		@SuppressWarnings("resource")
		ApplicationContext appContext = new AnnotationConfigApplicationContext(Application.class);
		Shutdowner shutdowner = appContext.getBean(Shutdowner.class);
		shutdowner.isShutdownScheduled();
	}

}
