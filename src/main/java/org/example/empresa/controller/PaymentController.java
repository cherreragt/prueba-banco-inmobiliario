package org.example.empresa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.empresa.dto.PaymentRequestDTO;
import org.example.empresa.dto.PaymentResponseDTO;
import org.example.empresa.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> processPayment(@Valid @RequestBody PaymentRequestDTO request, BindingResult result) {
        return ResponseEntity.ok(paymentService.processPayment(request, result));
    }
}
