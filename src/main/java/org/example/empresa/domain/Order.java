package org.example.empresa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    private Long id;
    private Long clientId;
    private List<OrderDetail> details;
    private PaymentOrder paymentOrder;
}
