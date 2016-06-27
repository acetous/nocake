package de.acetous.nocake;

import de.acetous.nocake.service.OpenBrowserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NocackeApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(NocackeApplication.class);
		springApplication.setHeadless(false);
		ConfigurableApplicationContext applicationContext = springApplication.run(args);

		applicationContext.getBean(OpenBrowserService.class).openUrl("/connect");
	}
}
