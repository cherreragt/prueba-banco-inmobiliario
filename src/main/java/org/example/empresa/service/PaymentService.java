package org.example.empresa.service;

import org.example.empresa.dto.PaymentRequestDTO;
import org.example.empresa.dto.PaymentResponseDTO;
import org.example.empresa.exception.BadRequestException;
import org.example.empresa.interfaces.IPaymentService;
import org.example.empresa.utils.Validator;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Objects;
import java.util.UUID;

@Service
public class PaymentService implements IPaymentService {
    public PaymentResponseDTO processPayment(PaymentRequestDTO request, BindingResult result) {
        Validator.validate(result);

        if (request.getAmount().doubleValue() <= 0) {
            throw new BadRequestException("El monto debe ser mayor a 0");
        }

        if (!isValidCard(request.getCardNumber())) {
            throw new BadRequestException("Número de tarjeta inválido");
        }

        String transactionId = UUID.randomUUID().toString();

        return new PaymentResponseDTO("SUCCESS", "Pago procesado exitosamente", transactionId);
    }

    private boolean isValidCard(String cardNumber) {
        return !Objects.isNull(cardNumber) && cardNumber.matches("\\d{16}");
    }
}
