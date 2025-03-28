package org.example.empresa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "username is required")
    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    @NotBlank(message = "password is required")
    private String password;
}
