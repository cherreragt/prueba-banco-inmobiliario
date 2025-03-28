package org.example.empresa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.empresa.dto.LoginRequestDTO;
import org.example.empresa.dto.LoginResponseDTO;
import org.example.empresa.dto.RequestDTO;
import org.example.empresa.exception.UnauthorizedException;
import org.example.empresa.repository.HttpRequest;
import org.example.empresa.utils.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final HttpRequest<LoginResponseDTO> httpRequest;

    @Value("${auth.host}")
    private String authHost;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO, BindingResult result) throws JsonProcessingException {
        Validator.validate(result);

        Map<String, String> headers = Map.of("Content-Type", "application/json");

        RequestDTO request = new RequestDTO.Builder()
                .host(authHost)
                .method("POST")
                .headers(headers)
                .body(loginRequestDTO)
                .build();

        var response = httpRequest.request(request, LoginResponseDTO.class);

        if (!response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            throw new UnauthorizedException("Invalid credentials");
        }

        return response.getBody();
    }
}
