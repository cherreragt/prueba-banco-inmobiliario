package org.example.empresa.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.empresa.dto.RequestDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class HttpRequest<T> {
    private final RestTemplate restTemplate;

    private HttpHeaders headers(Map<String, String> headers) {
        if (Objects.isNull(headers)) {
            return new HttpHeaders();
        }
        var httpHeaders = new HttpHeaders();
        headers.forEach(httpHeaders::add);
        return httpHeaders;
    }

    private String body(Object body) throws JsonProcessingException {
        if (Objects.isNull(body)) {
            return "";
        }

        var om = new ObjectMapper();
        return om.writeValueAsString(body);
    }

    private String urlParse(RequestDTO dto) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(dto.getHost());

        if (dto.getParams() != null) {
            for (Map.Entry<String, String> entry : dto.getParams().entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }

        return builder.toUriString();
    }

    public ResponseEntity<T> request(RequestDTO dto, Class<T> responseType) throws JsonProcessingException {
        String uri = urlParse(dto);
        String body = body(dto.getBody());

        return restTemplate.exchange(
                uri,
                HttpMethod.valueOf(dto.getMethod()),
                new HttpEntity<>(body, headers(dto.getHeaders())),
                responseType
        );
    }
}
