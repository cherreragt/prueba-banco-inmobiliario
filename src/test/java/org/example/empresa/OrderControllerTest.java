package org.example.empresa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.empresa.dto.CartDTO;
import org.example.empresa.dto.CartResponseDTO;
import org.example.empresa.dto.LoginResponseDTO;
import org.example.empresa.dto.ProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@SpringBootTest
class OrderControllerTest {

    @MockitoBean
    private RestTemplate restTemplate;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    ProductDTO getProduct() throws JsonProcessingException {

        var product = """
                {
                    "id": 1,
                    "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    "price": 109.95,
                    "description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                    "category": "men's clothing",
                    "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                    "rating": {
                        "rate": 3.9,
                        "count": 120
                    }
                }
                """;
        return objectMapper.readValue(product, ProductDTO.class);
    }

    CartDTO cart()  throws JsonProcessingException {
        var cart = """
                {
                    "id": 1,
                    "userId": 1,
                    "date": "2020-03-02T00:00:00.000Z",
                    "products": [
                        {
                            "productId": 1,
                            "quantity": 4
                        },
                        {
                            "productId": 2,
                            "quantity": 1
                        },
                        {
                            "productId": 3,
                            "quantity": 6
                        }
                    ],
                    "__v": 0
                }
                """;
        return objectMapper.readValue(cart, CartDTO.class);
    }

    CartResponseDTO getCart() throws JsonProcessingException {

        var cart = """
                {
                    "id": 11,
                    "userId": 1,
                    "products": [
                        {
                            "id": 1,
                            "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                            "price": 109.95,
                            "description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                            "category": "men's clothing",
                            "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                            "rating": {
                                "rate": 3.9,
                                "count": 120
                            }
                        }
                    ]
                }
                """;
        return objectMapper.readValue(cart, CartResponseDTO.class);
    }

    @Test
    void order() throws Exception {
        var response = getCart();
        var productDTO = getProduct();

        Mockito.when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(Object.class)))
                .thenReturn(new ResponseEntity<>(productDTO, HttpStatus.OK))
                .thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        var body = """
                {
                    "id": 1,
                    "userId": 1,
                    "products": [
                        {
                            "productId": 12,
                            "quantity": 2
                        }
                    ]
                }
                """;

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/order")
                .content(body)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse res = result.getResponse();

        Assertions.assertEquals(200, res.getStatus());
    }

    @Test
    void getOrders() throws Exception {
        var response = getCart();

        Mockito.when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(Object.class)))
                .thenReturn(new ResponseEntity<>(List.of(response), HttpStatus.OK));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/order")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse res = result.getResponse();

        Assertions.assertEquals(200, res.getStatus());
    }

    @Test
    void getOrder() throws Exception {
        var response = cart();

        Mockito.when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(Object.class)))
                .thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/order/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse res = result.getResponse();

        Assertions.assertEquals(200, res.getStatus());
    }

    @Test
    void modifyOrder() throws Exception {
        var response = getCart();
        var productDTO = getProduct();

        Mockito.when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(Object.class)))
                .thenReturn(new ResponseEntity<>(productDTO, HttpStatus.OK))
                .thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        var body = """
                {
                    "id": 1,
                    "userId": 1,
                    "products": [
                        {
                            "productId": 12,
                            "quantity": 2
                        }
                    ]
                }
                """;

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/order")
                .content(body)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse res = result.getResponse();

        Assertions.assertEquals(200, res.getStatus());
    }
}
