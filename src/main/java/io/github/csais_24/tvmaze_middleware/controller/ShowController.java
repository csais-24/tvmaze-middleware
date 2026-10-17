package io.github.csais_24.tvmaze_middleware.controller;

import io.github.csais_24.tvmaze_middleware.dto.ShowResponse;
import io.github.csais_24.tvmaze_middleware.service.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
