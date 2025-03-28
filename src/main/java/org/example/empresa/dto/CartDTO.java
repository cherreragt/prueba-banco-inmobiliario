package org.example.empresa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;
    @NotNull(message = "userId is required")
    private Long userId;
    private Date date;
    @NotNull(message = "products is required")
    @NotEmpty(message = "products are required")
    private List<OrderDTO> products;
    @JsonIgnore
    private Integer __v;
}
