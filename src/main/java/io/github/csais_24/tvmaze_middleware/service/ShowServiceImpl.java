package io.github.csais_24.tvmaze_middleware.service;

import io.github.csais_24.tvmaze_middleware.client.TvMazeClient;
import io.github.csais_24.tvmaze_middleware.document.ShowCache;
import io.github.csais_24.tvmaze_middleware.document.ShowComment;
import io.github.csais_24.tvmaze_middleware.dto.*;
import io.github.csais_24.tvmaze_middleware.repository.ShowCacheRepository;
import io.github.csais_24.tvmaze_middleware.repository.ShowCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService{

    private final TvMazeClient tvMazeClient;
    private final ShowCacheRepository showCacheRepository;
    private final ShowCommentRepository showCommentRepository;

    public ShowServiceImpl(
            TvMazeClient tvMazeClient,
            ShowCacheRepository showCacheRepository,
            ShowCommentRepository showCommentRepository){
        this.tvMazeClient = tvMazeClient;
        this.showCacheRepository = showCacheRepository;
        this.showCommentRepository = showCommentRepository;
    }

    @Override
    public List<ShowResponse> searchShows(String query) {
        List<TvMazeSearchItem> tvMazeSearchItem = tvMazeClient.searchShows(query);

        return tvMazeSearchItem
                .stream()
                .map(this::toShowResponse)
                .toList();
    }

    @Override
    public Map<String, Object> getShowById(Long id) {
        Map<String, Object> show = showCacheRepository.findById(id)
                .map(ShowCache::getData)
                .orElseGet(() -> {
                    Map<String, Object> fetchedShow = tvMazeClient.findShowById(id);

                    ShowCache showToCache = new ShowCache();
                    showToCache.setId(id);
                    showToCache.setData(fetchedShow);

                    showCacheRepository.save(showToCache);
                    return fetchedShow;
                });

        List<Map<String, Object>> comments = showCommentRepository.findByShowId(id)
                .stream()
                .map(comment -> Map.<String, Object>of(
                        "comment", comment.getComment(),
                        "rating", comment.getRating()
                ))
                .toList();

        show.put("comments", comments);

        return show;
    }

    @Override
    public ShowCommentResponse createComment(Long showId, CreateCommentRequest request) {
        ShowComment showComment = new ShowComment();
        showComment.setShowId(showId);
        showComment.setComment(request.comment());
        showComment.setRating(request.rating());
        ShowComment savedShowComment = showCommentRepository.save(showComment);
        return new ShowCommentResponse(
                savedShowComment.getId(),
                savedShowComment.getShowId(),
                savedShowComment.getComment(),
                savedShowComment.getRating());
    }

    private ShowResponse toShowResponse(TvMazeSearchItem item) {
        TvMazeShow show = item.show();

        String channel = null;

        if (show.network() != null) {
            channel = show.network().name();
        } else if (show.webChannel() != null) {
            channel = show.webChannel().name();
        }

        List<CommentResponse> comments = showCommentRepository
                .findByShowId(show.id())
                .stream()
                .map(comment -> new CommentResponse(
                        comment.getComment(),
                        comment.getRating()
                ))
                .toList();

        return new ShowResponse(
                show.id(),
                show.name(),
                channel,
                show.summary(),
                show.genres(),
                comments
        );
    }
}
