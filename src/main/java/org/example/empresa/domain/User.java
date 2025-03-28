package org.example.empresa.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Long id;

    @NotEmpty
    @NotBlank
    @Max(255)
    private String username;

    @NotEmpty
    @NotBlank
    @Max(255)
    private String email;

    private Name name;

    @NotEmpty
    @NotBlank
    @Max(255)
    private String password;
}
