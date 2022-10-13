package es.tirea.cima.mspocws.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import es.tirea.cima.comun.config.CimaConfigProperties;
import es.tirea.cima.mspocws.Application;
import es.tirea.cima.mspocws.cxf.ItemService;
import es.tirea.cima.mspocws.cxf.ItemServiceImpl;

@Configuration
public class WsConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private CimaConfigProperties serverConfig;


	@Bean
    public ServletRegistrationBean<CXFServlet> cxfDispatcherServlet() {
		return new ServletRegistrationBean<>(new CXFServlet(), "/soap-api/*");
    }
	
	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
	        return new SpringBus();
	    }
	
	@Bean
    public ItemService itemService() {
        return new ItemServiceImpl();
    }

	@Bean
	public Endpoint endpoint() {
	    EndpointImpl endpoint = new EndpointImpl(springBus(), itemService());
	    endpoint.publish("/itemService");
	    return endpoint;
	}
	
	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = serverConfig.getCors();
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            log.info("Registering CORS filter");            
            source.registerCorsConfiguration("/**", config);
        }
        return new CorsFilter(source);
    }
    
}
