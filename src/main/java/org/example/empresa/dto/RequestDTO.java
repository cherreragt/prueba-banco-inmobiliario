package org.example.empresa.dto;

import lombok.Getter;

import java.util.Map;
import java.util.Objects;

@Getter
public class RequestDTO {
    private final String host;
    private final String method;
    private final Object body;
    private final Map<String, String> headers;
    private final Map<String, String> params;

    private RequestDTO(Builder builder) {
        this.host = builder.host;
        this.method = builder.method;
        this.body = builder.body;
        this.headers = builder.headers;
        this.params = builder.params;
    }


    public static class Builder {
        private String host;
        private String method;
        private Object body;
        private Map<String, String> headers;
        private Map<String, String> params;

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder method(String method) {
            if (Objects.isNull(method)) {
                method = "GET";
            }
            this.method = method;
            return this;
        }

        public Builder body(Object body) {
            this.body = body;
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder params(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public RequestDTO build() {
            return new RequestDTO(this);
        }
    }
}
