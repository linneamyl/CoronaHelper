package hh.linneamyl.coronahelper;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.linneamyl.coronahelper.web.CoronahelperController;


@RunWith(SpringRunner.class)
@SpringBootTest
class CoronahelperApplicationTests {

	
	// Testaa ettei ohjelman controller ole tyhjä
	
	@Autowired
	private CoronahelperController controller;
	@Test
	
	public void contextLoads() {
		assertThat(controller).isNotNull();
		   
	}
}


