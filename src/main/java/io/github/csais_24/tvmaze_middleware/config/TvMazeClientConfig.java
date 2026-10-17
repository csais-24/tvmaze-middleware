package io.github.csais_24.tvmaze_middleware.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class TvMazeClientConfig {

    @Bean
    public RestClient tvMazeRestClient(){
        return RestClient.builder()
                .baseUrl("https://api.tvmaze.com")
                .build();
    }
}
