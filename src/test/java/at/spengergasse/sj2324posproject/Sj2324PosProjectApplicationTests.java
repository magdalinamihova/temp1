package at.spengergasse.sj2324posproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class Sj2324PosProjectApplicationTests {

	public static void main(String[] args) {
		SpringApplication.from(Sj2324PosProjectApplicationTests::main)
				.with(TestContainerConfiguration.class)
				.run(args);
	}

}
