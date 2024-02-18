package at.spengergasse.sj2324posproject;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class TestMain{

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        final int localPort = 25432;
        final int containerPort = 5432;
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.1-alpine"))
                .withCreateContainerCmdModifier(cmd -> {
                    cmd.withName("sj2324-pos-postgres");
                    cmd.withHostConfig(new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(localPort), new ExposedPort(containerPort))));
                })
                .withReuse(true);
    }
        public static void main(String[] args) {
            SpringApplication.from(Sj2324PosProjectApplication::main)
                    .with(TestMain.class)
                    .run(args);
        }
    }
