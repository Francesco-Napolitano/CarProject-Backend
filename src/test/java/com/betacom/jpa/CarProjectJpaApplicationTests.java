package com.betacom.jpa;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SelectClasses({VeicoloControllerTest.class,
				MacchinaControllerTest.class
				})


@SpringBootTest
class CarProjectJpaApplicationTests {

	@Test
	void contextLoads() {
	}

}
