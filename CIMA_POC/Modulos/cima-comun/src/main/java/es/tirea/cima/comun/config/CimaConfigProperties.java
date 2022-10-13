package es.tirea.cima.comun.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;


@ConfigurationProperties(prefix = "cima.web")
public class CimaConfigProperties {

	private CorsConfiguration cors = new CorsConfiguration();
	
	public CorsConfiguration getCors() {
	        return cors;
	    }

	public void setCors(CorsConfiguration cors) {
		this.cors = cors;
	}
	
	
	

	
}
