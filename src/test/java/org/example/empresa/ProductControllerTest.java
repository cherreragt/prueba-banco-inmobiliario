package org.example.empresa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
class ProductControllerTest {

    @MockitoBean
    private RestTemplate restTemplate;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    ProductDTO product() throws JsonProcessingException {
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

    @Test
    void getProduct() throws Exception {
        Mockito.when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(Object.class)))
                .thenReturn(new ResponseEntity<>(product(), HttpStatus.OK));


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void getProducts() throws Exception {
        Mockito.when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(Object.class)))
                .thenReturn(new ResponseEntity<>(List.of(product()), HttpStatus.OK));


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void deleteProduct() throws Exception {
        Mockito.when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(Object.class)))
                .thenReturn(new ResponseEntity<>(List.of(product()), HttpStatus.OK));


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/product/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void updateProduct() throws Exception {
        Mockito.when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(Object.class)))
                .thenReturn(new ResponseEntity<>(product(), HttpStatus.OK));

        var body = """
                {
                    "id": 21,
                    "title": "PRUEBA PRODUCTO",
                    "price": 22.21,
                    "description": "DESCRIPCION",
                    "category": "ENTRETENIMIENTO",
                    "image": "http://GGGGGG.com"
                }
                """;

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/product")
                .content(body)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void createProduct() throws Exception {
        Mockito.when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(Object.class)))
                .thenReturn(new ResponseEntity<>(product(), HttpStatus.OK));

        var body = """
                {
                    "id": 21,
                    "title": "PRUEBA PRODUCTO",
                    "price": 22.21,
                    "description": "DESCRIPCION",
                    "category": "ENTRETENIMIENTO",
                    "image": "http://GGGGGG.com"
                }
                """;

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/product")
                .content(body)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assertions.assertEquals(200, response.getStatus());
    }
}
