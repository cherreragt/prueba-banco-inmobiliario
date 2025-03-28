package org.example.empresa.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    @NotNull(message = "Número de tarjeta no puede ser nulo")
    @NotEmpty(message = "Número de tarjeta no puede ser vacío")
    private String cardNumber;

    @NotNull(message = "Nombre del titular no puede ser nulo")
    @NotEmpty(message = "Nombre del titular no puede ser vacío")
    private String cardHolder;

    @NotNull(message = "Fecha de expiración no puede ser nulo")
    @NotEmpty(message = "Fecha de expiración no puede ser vacío")
    private String expirationDate;

    @NotNull(message = "CVV no puede ser nulo")
    @NotEmpty(message = "CVV no puede ser vacío")
    @Size(min = 3, max = 3, message = "CVV debe tener 3 dígitos")
    private String cvv;

    @NotNull(message = "Monto no puede ser nulo")
    private BigDecimal amount;

    @NotNull(message = "ID de la orden no puede ser nulo")
    private Long orderId;
}
