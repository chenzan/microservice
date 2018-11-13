package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import java.awt.*;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class CzGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CzGatewayApplication.class, args);
	}
}
