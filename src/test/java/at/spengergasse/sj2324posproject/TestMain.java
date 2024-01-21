package at.spengergasse.sj2324posproject;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestMain{

        public static void main(String[] args) {
            SpringApplication.from(Sj2324PosProjectApplication::main)
                    .with(TestMain.class)
                    .run(args);
        }

    }
