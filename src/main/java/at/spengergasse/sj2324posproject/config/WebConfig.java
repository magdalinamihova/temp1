package at.spengergasse.sj2324posproject.config;

import at.spengergasse.sj2324posproject.service.PasswordPolicyConfigurationProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(PasswordPolicyConfigurationProperties.class)
public class WebConfig {
     public static final String BASE_URL = "https://httpbin.org";

    @Bean
    public RestClient httpBin(RestClient.Builder builder) {
        return builder.requestFactory(new JdkClientHttpRequestFactory())
                .baseUrl(BASE_URL)
                .build();
    }


}