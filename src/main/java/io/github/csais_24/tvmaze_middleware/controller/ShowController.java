package io.github.csais_24.tvmaze_middleware.controller;

import io.github.csais_24.tvmaze_middleware.dto.CreateCommentRequest;
import io.github.csais_24.tvmaze_middleware.dto.ShowCommentResponse;
import io.github.csais_24.tvmaze_middleware.dto.ShowResponse;
import io.github.csais_24.tvmaze_middleware.service.ShowService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService){
        this.showService = showService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<ShowResponse>> searchShows(@RequestParam(name = "q", required = true) String q){
        return new ResponseEntity<>(showService.searchShows(q), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getShowById(@PathVariable Long id){

        Map<String, Object> show = showService.getShowById(id);
        return new ResponseEntity<>(show, HttpStatus.OK);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<ShowCommentResponse> createShowComment(
            @PathVariable Long id,
            @Valid @RequestBody CreateCommentRequest createCommentRequest) {
        ShowCommentResponse response = showService.createComment(id, createCommentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
