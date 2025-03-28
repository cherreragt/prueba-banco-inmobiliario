package org.example.empresa.interfaces;

import org.example.empresa.dto.PaymentRequestDTO;
import org.example.empresa.dto.PaymentResponseDTO;
import org.springframework.validation.BindingResult;

public interface IPaymentService {
    PaymentResponseDTO processPayment(PaymentRequestDTO request, BindingResult result);
}
