package com.kns.tenquest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TenquestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenquestApplication.class, args);
	}

}
