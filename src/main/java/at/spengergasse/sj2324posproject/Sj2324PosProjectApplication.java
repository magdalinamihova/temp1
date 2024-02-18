package at.spengergasse.sj2324posproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Sj2324PosProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sj2324PosProjectApplication.class, args);
	}

}
