package io.github.csais_24.tvmaze_middleware.repository;

import io.github.csais_24.tvmaze_middleware.document.ShowComment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShowCommentRepository extends MongoRepository<ShowComment, String> {
    List<ShowComment> findByShowId(Long id);
}
