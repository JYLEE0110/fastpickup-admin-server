package org.fktm.fastpickup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.fktm.fastpickup.**.mappers")
public class FastpickupApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastpickupApplication.class, args);
	}

}
