package es.tirea.cima.mspocws;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import es.tirea.cima.comun.config.CorsProperties;

@SpringBootTest
@ActiveProfiles("local")
class ApplicationTests {
	
	 @Autowired
	 private CorsProperties corsProperties;

	@Test
	void contextLoads() {
	}
	
	@Test
	void testPrps() {
		//System.out.println(serverConfig.getAddress());
		corsProperties.getCors().getAllowedOrigins().stream().forEach(origin->{
			System.out.println(origin);
		});
		
		System.out.println("y el defaulkt??????????????");
		
		
		System.out.println(corsProperties.getCors().getMaxAge());
		
		
		corsProperties.getCors().getAllowedMethods().stream().forEach(method->{
			System.out.println(method);
		});
	}
	
	
	
}
