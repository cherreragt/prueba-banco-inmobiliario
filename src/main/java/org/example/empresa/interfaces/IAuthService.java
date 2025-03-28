package org.example.empresa.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.empresa.dto.LoginRequestDTO;
import org.example.empresa.dto.LoginResponseDTO;
import org.springframework.validation.BindingResult;

public interface IAuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO, BindingResult result) throws JsonProcessingException;
}
