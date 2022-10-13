package es.tirea.cima.mspocws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;

import es.tirea.cima.comun.config.BaseConfigurer;
import es.tirea.cima.comun.config.CimaConfigProperties;
import es.tirea.cima.comun.config.DefaultProfileUtil;

/**
 * @author luisusr
 *
 */
@SpringBootApplication
@EnableConfigurationProperties({ CimaConfigProperties.class })
public class Application extends SpringBootServletInitializer{
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		DefaultProfileUtil.addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();
		BaseConfigurer.logApplicationStartup(log, env);
	}
	
	// Used when deploying to a standalone servlet container, i.e. tomcat
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
