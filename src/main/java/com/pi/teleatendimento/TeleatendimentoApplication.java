package com.pi.teleatendimento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling	
@EnableAutoConfiguration
@ComponentScan("com.pi")
public class TeleatendimentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeleatendimentoApplication.class, args);
	}

}
