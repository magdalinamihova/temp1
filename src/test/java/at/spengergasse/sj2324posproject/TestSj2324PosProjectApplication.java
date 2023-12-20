package at.spengergasse.sj2324posproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestSj2324PosProjectApplication{

    public static void main(String[] args) {
        SpringApplication.from(Sj2324PosProjectApplication::main)
                .with(TestSj2324PosProjectApplication.class)
                .run(args);
    }
}
