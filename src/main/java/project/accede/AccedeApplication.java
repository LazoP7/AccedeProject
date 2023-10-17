package project.accede;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "project/accede")
public class AccedeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AccedeApplication.class, args);
	}

}
