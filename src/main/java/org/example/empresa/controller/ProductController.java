package org.example.empresa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.empresa.dto.ProductDTO;
import org.example.empresa.interfaces.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult result) throws JsonProcessingException {
        return ResponseEntity.ok(productService.createProduct(productDTO, result));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult result) throws JsonProcessingException {
        return ResponseEntity.ok(productService.updateProduct(productDTO, result));
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) throws JsonProcessingException {
        productService.deleteProduct(productId);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId) throws JsonProcessingException {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() throws JsonProcessingException {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
