package io.github.csais_24.tvmaze_middleware.repository;

import io.github.csais_24.tvmaze_middleware.document.ShowCache;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowCacheRepository extends MongoRepository<ShowCache, Long> {
}
