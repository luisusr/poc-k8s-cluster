package es.tirea.cima.comun.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

@Component
@ConfigurationProperties(prefix = "cors.config")
public class CorsProperties {

	private CorsConfiguration cors = new CorsConfiguration();
	
	public CorsConfiguration getCors() {
	        return cors;
	    }

	public void setCors(CorsConfiguration cors) {
		this.cors = cors;
	}
	
}
