package at.spengergasse.sj2324posproject.service.connectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HttpBinConnectorTest {

    private @Autowired HttpBinConnector httpBin;

    @Test
    void ensureRetrievalOfKeyReturnsAnything() {
        assertThat(httpBin.retrieveKey()).isNotNull();
        System.out.println(httpBin.retrieveKey());
    }
}