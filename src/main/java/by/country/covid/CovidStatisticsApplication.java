package by.country.covid;

import by.country.covid.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
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

}
