package io.github.csais_24.tvmaze_middleware.dto;

import java.util.List;

public record TvMazeShow(
        Long id,
        String name,
        TvMazeChannel network,
        TvMazeChannel webChannel,
        String summary,
        List<String> genres
) {
}
