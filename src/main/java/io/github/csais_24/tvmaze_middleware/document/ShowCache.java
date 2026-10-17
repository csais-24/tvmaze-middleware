package io.github.csais_24.tvmaze_middleware.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "show_cache")
public class ShowCache {

    @Id
    private Long id;
    private Map<String, Object> data;

    public Long getId() {
        return id;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
