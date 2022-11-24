package ec.org.isspol.mic.rest.generacionreporte;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean("cienteRest")
    public RestTemplate RegistrarRestTemplate() {
        return new RestTemplate();
    }

}
