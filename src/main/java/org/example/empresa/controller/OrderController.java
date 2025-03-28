package org.example.empresa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.empresa.dto.CartDTO;
import org.example.empresa.dto.CartResponseDTO;
import org.example.empresa.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService cartService;

    @PostMapping
    public ResponseEntity<CartResponseDTO> order(@Valid @RequestBody CartDTO cart, BindingResult result) throws JsonProcessingException {
        return ResponseEntity.ok(cartService.order(cart, result));
    }

    @PutMapping
    public ResponseEntity<CartResponseDTO> modifyOrder(@Valid @RequestBody CartDTO cart, BindingResult result) throws JsonProcessingException {
        return ResponseEntity.ok(cartService.modifyOrder(cart, result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long id) throws JsonProcessingException {
        return ResponseEntity.ok(cartService.getCart(id));
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> getCarts() throws JsonProcessingException {
        return ResponseEntity.ok(cartService.getCarts());
    }
}
