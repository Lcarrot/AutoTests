package ru.itis.auto.test.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UrlBuilder {

    private final String baseUrl;
    private final List<String> paths = new ArrayList<>();
    private final Map<String, String> params = new HashMap<>();

    public UrlBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public UrlBuilder addPath(String path) {
        paths.add(path);
        return this;
    }

    public UrlBuilder addQueryParams(String key, String value) {
        params.put(key, value);
        return this;
    }

    public void clearPath() {
        paths.clear();
    }

    public String buildUrl() {
        StringBuilder path = new StringBuilder(baseUrl);
        path.append(String.join("/", paths));
        if (!params.isEmpty()) {
            path.append("?").append(params.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&")));
        }
        return path.toString();
    }
}
