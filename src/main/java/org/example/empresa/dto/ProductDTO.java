package org.example.empresa.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotNull(message = "id is required")
    private Long id;

    @NotBlank(message = "title is required")
    @NotNull(message = "title is required")
    private String title;

    @NotNull(message = "price is required")
    @Min(value = 0, message = "price must be greater than or equal to 0")
    private Double price;

    @NotNull(message = "description is required")
    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "category is required")
    @NotBlank(message = "category is required")
    @Pattern(regexp="^(ENTRETENIMIENTO|CINE|HOGAR)$",message="description must be one of the following values: ENTREGA, CINE, HOGAR")
    private String category;

    @NotNull(message = "image is required")
    @NotBlank(message = "image is required")
    private String image;

    private RatingDTO rating;
}
