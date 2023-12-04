package org.fktm.fastpickup;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class FastpickupApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	public void connectionTest(){
		try (Connection connection = dataSource.getConnection()){
			log.info("Is Ok DataBase Connection");
		}catch(Exception e){
			log.info("Find Some Errors");
		}
	}

}
