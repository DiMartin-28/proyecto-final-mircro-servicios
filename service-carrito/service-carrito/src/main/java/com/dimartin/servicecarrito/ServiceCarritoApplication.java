package com.dimartin.servicecarrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceCarritoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCarritoApplication.class, args);
	}

}
