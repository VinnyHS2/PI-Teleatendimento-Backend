package com.pi.teleatendimento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan("com.pi")
class TeleatendimentoApplicationTests {
	
	@Test
	void contextLoads() {
	}

	@Test
	void sanityCheck(){
		assertEquals(1+1,2);
	}
	
}
