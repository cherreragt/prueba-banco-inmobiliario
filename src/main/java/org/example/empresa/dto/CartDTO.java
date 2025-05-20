package org.example.empresa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    @NotNull(message = "id is required")
    @Min(value = 1, message = "id must be greater than 0")
    private Long id;
    @NotNull(message = "userId is required") // se remueve para clase de QA
    @Min(value = 1, message = "userId must be greater than 0")
    private Long userId;
    private Date date;
    /*@NotNull(message = "products is required") // se remueve para clase de QA
    @NotEmpty(message = "products are required")*/
    private List<OrderDTO> products;
    @JsonIgnore
    private Integer __v;
}
