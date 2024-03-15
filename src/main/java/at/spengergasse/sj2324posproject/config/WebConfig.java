package at.spengergasse.sj2324posproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class WebConfig {
    public static final String BASE_URL = "https://httpbin.org";


    @Bean
    public RestClient httpBin(RestClient.Builder builder, @Value("${httpbin.org.uri}") String httpBinUri) {
        return builder.requestFactory(new JdkClientHttpRequestFactory())
                .baseUrl(httpBinUri)
                .build();
    }

    // #todo look up if further code needed

}
