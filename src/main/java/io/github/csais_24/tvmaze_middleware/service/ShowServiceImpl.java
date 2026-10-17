package io.github.csais_24.tvmaze_middleware.service;

import io.github.csais_24.tvmaze_middleware.client.TvMazeClient;
import io.github.csais_24.tvmaze_middleware.dto.ShowResponse;
import io.github.csais_24.tvmaze_middleware.dto.TvMazeSearchItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowService{

    private final TvMazeClient tvMazeClient;

    public ShowServiceImpl(TvMazeClient tvMazeClient){
        this.tvMazeClient = tvMazeClient;
    }

    @Override
    public List<ShowResponse> searchShows(String query) {
        List<TvMazeSearchItem> tvMazeSearchItem = tvMazeClient.searchShows(query);

        return tvMazeSearchItem
                .stream()
                .map(this::toShowResponse)
                .toList();
    }

    private ShowResponse toShowResponse(TvMazeSearchItem tvMazeSearchItem){
        var show = tvMazeSearchItem.show();
        String channel = show.network() != null
                ? show.network().name()
                : show.webChannel() != null
                ? show.webChannel().name()
                : null;
        return new ShowResponse(
                show.id(),
                show.name(),
                channel,
                show.summary(),
                show.genres()
        );
    }
}
