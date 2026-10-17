package io.github.csais_24.tvmaze_middleware.dto;

public record ShowCommentResponse(
        String id,
        Long showId,
        String comment,
        Integer rating
) {
}
