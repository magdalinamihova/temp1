package at.spengergasse.sj2324posproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {
    //public static final String BASE_URL = "https://httpbin.org";

    // #todo RestClient

    // RestClient & JdkClientHttpRequestFactory not under spring-boot-3.1.4 -> 3.2.2
    /*@Bean
    public RestClient httpBin(RestClient.Builder builder, @Value("${httpbin.org.uri}") String httpBinUri) {
        return builder.requestFactory(new JdkClientHttpRequestFactory())
                .baseUrl(httpBinUri)
                .build();
    }*/


    // useless
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new SimpleClientHttpRequestFactory());
    }

    /*
    @Bean
    public RestService restService(RestTemplate restTemplate) {
        return new RestService(restTemplate);
    }*/

}
