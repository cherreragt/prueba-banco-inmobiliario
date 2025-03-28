package org.example.empresa.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.empresa.dto.ProductDTO;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IProductService {
    ProductDTO createProduct(ProductDTO productDTO, BindingResult result) throws JsonProcessingException;
    ProductDTO updateProduct(ProductDTO productDTO, BindingResult result) throws JsonProcessingException;
    void deleteProduct(Long productId) throws JsonProcessingException;
    ProductDTO getProduct(Long productId) throws JsonProcessingException;
    List<ProductDTO> getAllProducts() throws JsonProcessingException;
}
