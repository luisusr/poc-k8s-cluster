package es.tirea.cima.mspocws.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.tirea.cima.mspocws.cxf.ItemService;
import es.tirea.cima.mspocws.cxf.ItemServiceImpl;

@Configuration
public class WsConfiguration {


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

    
}
