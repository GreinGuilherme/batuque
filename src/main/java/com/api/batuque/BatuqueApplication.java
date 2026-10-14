package com.api.batuque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BatuqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatuqueApplication.class, args);
	}

}
