package org.example.empresa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.empresa.dto.ProductDTO;
import org.example.empresa.dto.RequestDTO;
import org.example.empresa.exception.ErrorInternalException;
import org.example.empresa.interfaces.IProductService;
import org.example.empresa.repository.HttpRequest;
import org.example.empresa.utils.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final HttpRequest<Object> httpRequest;
    private static final String PATH = "products/";
    private static final String ERROR_ON_REQUEST = "Error on request";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${store.host}")
    private String storeHost;

    private final Map<String, String> headers = Map.of("Content-Type", "application/json");

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, BindingResult result) throws JsonProcessingException {
        Validator.validate(result);
        productDTO.setId(null);

        RequestDTO request = new RequestDTO.Builder()
                .host(storeHost.concat(PATH))
                .method("POST")
                .body(productDTO)
                .headers(headers)
                .build();

        var response = httpRequest.request(request, Object.class);

        if (response.getStatusCode().isError()) {
            throw new ErrorInternalException(ERROR_ON_REQUEST);
        }

        return objectMapper.convertValue(response.getBody(), ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, BindingResult result) throws JsonProcessingException {
        Validator.validate(result);

        RequestDTO request = new RequestDTO.Builder()
                .host(storeHost.concat(PATH).concat(productDTO.getId().toString()))
                .method("PUT")
                .body(productDTO)
                .headers(headers)
                .build();

        var response = httpRequest.request(request, Object.class);

        if (response.getStatusCode().isError()) {
            throw new ErrorInternalException(ERROR_ON_REQUEST);
        }

        return objectMapper.convertValue(response.getBody(), ProductDTO.class);
    }

    @Override
    public void deleteProduct(Long productId) throws JsonProcessingException {
        RequestDTO request = new RequestDTO.Builder()
                .host(storeHost.concat(PATH).concat(productId.toString()))
                .method("DELETE")
                .headers(headers)
                .build();

        var response = httpRequest.request(request, Object.class);

        if (response.getStatusCode().isError()) {
            throw new ErrorInternalException(ERROR_ON_REQUEST);
        }
    }

    @Override
    public ProductDTO getProduct(Long productId) throws JsonProcessingException {
        RequestDTO request = new RequestDTO.Builder()
                .host(storeHost.concat(PATH).concat(productId.toString()))
                .method("GET")
                .headers(headers)
                .build();

        var response = httpRequest.request(request, Object.class);

        if (response.getStatusCode().isError()) {
            throw new ErrorInternalException(ERROR_ON_REQUEST);
        }

        return objectMapper.convertValue(response.getBody(), ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProducts() throws JsonProcessingException {
        RequestDTO request = new RequestDTO.Builder()
                .host(storeHost.concat(PATH))
                .method("GET")
                .headers(headers)
                .build();

        var response = httpRequest.request(request, Object.class);

        if (response.getStatusCode().isError()) {
            throw new ErrorInternalException(ERROR_ON_REQUEST);
        }

        @SuppressWarnings("unchecked")
        var products = (List<ProductDTO>) response.getBody();

        return products;
    }
}
