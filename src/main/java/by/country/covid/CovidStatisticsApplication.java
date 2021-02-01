package by.country.covid;

import by.country.covid.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableCaching
public class CovidStatisticsApplication {

	@Autowired
	DataService service;

	public static void main(String[] args) {
		SpringApplication.run(CovidStatisticsApplication.class, args);
	}

	//Get's information from public api when application stats
	//And saves it in memory
	@Component
	public class ApplicationRunnerBean implements ApplicationRunner {
		@Override
		public void run(ApplicationArguments arg0) throws Exception {
			service.getList();
		}
	}

	//For Heroku, port is not constant
	@Bean
	public TomcatServletWebServerFactory tomcatCustomizer() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.addConnectorCustomizers((connector -> {

			/**
			 * Gets PORT env variable and sets it as tomcat server port.
			 * In Heroku env, the PORT env variable must be used by tomcat
			 * because if not the process will fail.
			 * Error R10 (Boot timeout) -> Web process failed to bind to $PORT within 60 seconds of launch
			 */
			String envPort = System.getenv("PORT");
			// If PORT is NULL, we'll use the default port
			if (envPort != null) {
				connector.setPort(Integer.parseInt(envPort));
			}
		}));
		return factory;
	}

}
