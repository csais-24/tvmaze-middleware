package io.github.csais_24.tvmaze_middleware.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCommentRequest(
        @NotBlank
        String comment,
        @Min(0)
        @Max(5)
        @NotNull
        Integer rating
) {
}
