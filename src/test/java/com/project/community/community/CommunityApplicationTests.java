package com.project.community.community;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = "com.project.community..community.mapper")
class CommunityApplicationTests {

	@Test
	void contextLoads() {
	}

}
