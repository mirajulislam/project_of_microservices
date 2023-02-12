package com.microservice.productservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.repo.ProductRepository;


@SpringBootTest
@Testcontainers
class ProductServiceApplicationTests {
	 @Container
	    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	 @Autowired
	    private MockMvc mockMvc;
	 @Autowired
	    private ObjectMapper objectMapper;
	 @Autowired
	    private ProductRepository productRepository;
	 
	 @DynamicPropertySource
	    static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
	        dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	 @Test
	    void shouldCreateProduct() throws Exception {
	        ProductRequest productRequest = getProductRequest();
	        String productRequestString = objectMapper.writeValueAsString(productRequest);
	        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(productRequestString))
	                .andExpect(status().isCreated());
	        Assertions.assertEquals(2, productRepository.findAll().size());
	    }
	 private ProductRequest getProductRequest() {
	        return ProductRequest.builder()
	                .name("iPhone 13")
	                .description("iPhone 13")
	                .price(BigDecimal.valueOf(1200))
	                .build();
	    }

}
