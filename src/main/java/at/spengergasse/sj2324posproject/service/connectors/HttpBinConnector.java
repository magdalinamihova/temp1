package at.spengergasse.sj2324posproject.service.connectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequiredArgsConstructor
@Slf4j

@Component
public class HttpBinConnector {

    private final RestTemplate httpBin; // #todo RestClient

    public String retrieveKey() {
        /*
        Map<String, String> response = httpBin.get()
                .uri("/uuid")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<Map<String, String>>() {
                })
                .getBody();
        String key = response.get("uuid");
        log.debug("Retrieved key: {}", key);
        return key;
    }*/
        return "";
    }
}
