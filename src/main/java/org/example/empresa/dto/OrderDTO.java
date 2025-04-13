package org.example.empresa.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @NotNull(message = "productId is required")
    private Long productId;
    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "quantity must be greater than 0")
    private Long quantity;
}
