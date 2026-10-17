package io.github.csais_24.tvmaze_middleware.service;

import io.github.csais_24.tvmaze_middleware.dto.CreateCommentRequest;
import io.github.csais_24.tvmaze_middleware.dto.ShowCommentResponse;
import io.github.csais_24.tvmaze_middleware.dto.ShowResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ShowService {
    List<ShowResponse> searchShows(String query);
    Map<String, Object> getShowById(Long id);
    ShowCommentResponse createComment(Long showId, CreateCommentRequest request);
}
