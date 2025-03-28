package org.example.empresa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO {
    private Long id;
    private Long userId;
    private List<ProductDTO> products;
}
