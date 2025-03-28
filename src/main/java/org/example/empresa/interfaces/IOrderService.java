package org.example.empresa.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.empresa.dto.CartDTO;
import org.example.empresa.dto.CartResponseDTO;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IOrderService {
    CartResponseDTO order(CartDTO cart, BindingResult result) throws JsonProcessingException;
    CartResponseDTO modifyOrder(CartDTO cart, BindingResult result) throws JsonProcessingException;
    CartDTO getCart(Long id) throws JsonProcessingException;
    List<CartDTO> getCarts() throws JsonProcessingException;
}
