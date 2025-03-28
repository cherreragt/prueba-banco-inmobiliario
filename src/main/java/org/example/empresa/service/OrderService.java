package org.example.empresa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.empresa.dto.CartDTO;
import org.example.empresa.dto.CartRequestDTO;
import org.example.empresa.dto.CartResponseDTO;
import org.example.empresa.dto.RequestDTO;
import org.example.empresa.exception.BadRequestException;
import org.example.empresa.interfaces.IOrderService;
import org.example.empresa.repository.HttpRequest;
import org.example.empresa.utils.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final ProductService productService;
    private final HttpRequest<Object> httpRequest;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, String> headers = Map.of("Content-Type", "application/json");

    @Value("${store.host}")
    private String storeHost;

    public CartResponseDTO order(CartDTO cart, BindingResult result) throws JsonProcessingException {
        Validator.validate(result);

        var cartRequestDTO = new CartRequestDTO();
        cartRequestDTO.setUserId(cart.getUserId());
        cartRequestDTO.setId(cart.getId());
        cartRequestDTO.setProducts(new ArrayList<>());

        for (var productDTO : cart.getProducts()) {
            try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
                var validator = factory.getValidator();
                var violations = validator.validate(productDTO);
                if (!violations.isEmpty()) {
                    throw new BadRequestException(violations.stream().toList().get(0).getMessage());
                }
            }

            var product = productService.getProduct(productDTO.getProductId());
            if (Objects.isNull(product) || Objects.isNull(product.getId())) {
                throw new BadRequestException("Producto inexiste");
            }
            product.setPrice(product.getPrice() * productDTO.getQuantity());
            cartRequestDTO.getProducts().add(product);
        }

        RequestDTO request = new RequestDTO.Builder()
                .host(storeHost.concat("carts"))
                .method("POST")
                .headers(headers)
                .body(cartRequestDTO)
                .build();

        var response = httpRequest.request(request, Object.class);
        var body = objectMapper.convertValue(response.getBody(), CartResponseDTO.class);
        body.setTotal(body.processTotal());

        return body;
    }

    public CartResponseDTO modifyOrder(CartDTO cart, BindingResult result) throws JsonProcessingException {
        Validator.validate(result);

        var cartRequestDTO = new CartRequestDTO();
        cartRequestDTO.setUserId(cart.getUserId());
        cartRequestDTO.setId(cart.getId());
        cartRequestDTO.setProducts(new ArrayList<>());

        for (var productDTO : cart.getProducts()) {
            try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
                var validator = factory.getValidator();
                var violations = validator.validate(productDTO);
                if (!violations.isEmpty()) {
                    throw new BadRequestException(violations.stream().toList().get(0).getMessage());
                }
            }

            var product = productService.getProduct(productDTO.getProductId());
            if (Objects.isNull(product) || Objects.isNull(product.getId())) {
                throw new BadRequestException("Producto inexiste");
            }
            cartRequestDTO.getProducts().add(product);
        }

        RequestDTO request = new RequestDTO.Builder()
                .host(storeHost.concat("carts/".concat(cart.getId().toString())))
                .method("PUT")
                .headers(headers)
                .body(cartRequestDTO)
                .build();

        var response = httpRequest.request(request, Object.class);

        return objectMapper.convertValue(response.getBody(), CartResponseDTO.class);
    }

    @Override
    public CartDTO getCart(@NotNull Long id) throws JsonProcessingException {
        RequestDTO request = new RequestDTO.Builder()
                .host(storeHost.concat("carts/".concat(id.toString())))
                .method("GET")
                .headers(headers)
                .build();

        var response = httpRequest.request(request, Object.class);

        return objectMapper.convertValue(response.getBody(), CartDTO.class);
    }

    @Override
    public List<CartDTO> getCarts() throws JsonProcessingException {
        RequestDTO request = new RequestDTO.Builder()
                .host(storeHost.concat("carts/"))
                .method("GET")
                .headers(headers)
                .build();

        var response = httpRequest.request(request, Object.class);

        return (List<CartDTO>) response.getBody();
    }
}
