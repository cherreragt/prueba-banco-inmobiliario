package org.example.empresa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO extends CartRequestDTO {
    private Double total;

    public Double processTotal() {
        total = 0.0;
        for (ProductDTO product : getProducts()) {
            if (!Objects.isNull(product.getPrice())) {
                total += product.getPrice();
            }
        }
        return total;
    }
}
