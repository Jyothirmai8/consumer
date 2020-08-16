package com.consumer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.consumer.controller.ConsumerController;

@SpringBootApplication
@EnableEurekaClient
public class ConsumerApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ConsumerApplication.class, args);
		
		ConsumerController cc = ctx.getBean(ConsumerController.class);
		cc.accessEmployee();
	}

	@Bean
	public ConsumerController createClass(){
		return new ConsumerController();
	}
	
}
