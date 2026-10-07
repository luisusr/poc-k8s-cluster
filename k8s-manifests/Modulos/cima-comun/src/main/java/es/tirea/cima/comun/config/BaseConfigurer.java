package es.tirea.cima.comun.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;

public class BaseConfigurer {

	
	/**
	 * Loguea el arranque de un módulo
	 * 
	 * @param log la variable de log de slf4j
	 * @param env el entorno
	 */
	public static void logApplicationStartup(Logger log, Environment env) {
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		String serverPort = env.getProperty("server.port");
		String contextPath = env.getProperty("server.servlet.context-path");
		String jaegerAgent = env.getProperty("opentracing.jaeger.udp-sender.host");
		if (StringUtils.isBlank(contextPath)) {
			contextPath = "/";
		}
		String hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.warn("No se puede determinar el nombre de la maquina, usando `localhost`");
		}
		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Applicacion '{}' Arrancada correctamente! URLs de acceso:\n\t"
						+ "Local: \t\t{}://localhost:{}{}\n\t" + "External: \t{}://{}:{}{}\n\t"
						+ "Profile(s): \t{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"), protocol, serverPort, contextPath, protocol, hostAddress,
				serverPort, contextPath, env.getActiveProfiles());

		String configServerStatus = env.getProperty("configserver.status");
		if (configServerStatus == null) {
			configServerStatus = "Config Server no configurado para la aplicacion:"
					+ env.getProperty("spring.application.name");
		}
		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Config Server: \t{}\n----------------------------------------------------------",
				configServerStatus);
		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Jaeger Agent Host: \t{}\n----------------------------------------------------------",
						jaegerAgent);
	}

}
