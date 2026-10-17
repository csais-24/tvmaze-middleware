package io.github.csais_24.tvmaze_middleware.service;

import io.github.csais_24.tvmaze_middleware.dto.ShowResponse;

import java.util.List;
import java.util.Optional;

public interface ShowService {
    List<ShowResponse> searchShows(String query);
}
