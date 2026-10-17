package io.github.csais_24.tvmaze_middleware.client;

import io.github.csais_24.tvmaze_middleware.dto.TvMazeSearchItem;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class TvMazeClient {

    private final RestClient restClient;

    public TvMazeClient(RestClient restClient){
        this.restClient = restClient;
    }

    public List<TvMazeSearchItem> searchShows(String query) {
        return restClient.get()
                .uri("/search/shows?q={query}", query)
                .retrieve()
                .body(new ParameterizedTypeReference<List<TvMazeSearchItem>>() {
                });
    }
}
