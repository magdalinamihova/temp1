package at.spengergasse.sj2324posproject.config;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {     // useless - if RestClient working -> get rid of this class

    // #todo RestClient

    /*private final RestTemplate restTemplate;

    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void fetchData() {
        String endpoint = "/get";
        String url = WebConfig.BASE_URL + endpoint;
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
    }*/
}
