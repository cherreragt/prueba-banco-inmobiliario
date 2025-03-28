package org.example.empresa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentOrder {
    private String paymentMethod;
    private String cardNumber;
    private String cardHolder;
    private String expirationDate;
    private String cvv;
    private String paymentStatus;
    private String paymentDate;
    private String paymentId;
    private String paymentAmount;
}
