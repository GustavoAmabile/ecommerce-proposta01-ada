package com.ecommerce.polotech;


import com.ecommerce.polotech.model.Cart;
import com.ecommerce.polotech.model.Customer;
import com.ecommerce.polotech.model.Product;
import com.ecommerce.polotech.repository.CartRepository;
import com.ecommerce.polotech.repository.CustomerRepository;
import com.ecommerce.polotech.service.CartService;
import com.ecommerce.polotech.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CarrinhoTesteIntegracao {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testarAdicionarProdutoAoCarrinho() throws Exception {
        // TODO
        Cart cart = new Cart();
        cartRepository.save(cart);
        Product product = new Product();
        product.setId(1L);
        product.setName("Produto 1");
        product.setDescription("Descrição do produto 1");
        product.setPrice(new BigDecimal(10));
        product.setCart(cart);
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Cliente um");
        customer.setEmail("cliente01@mail.com");
        customer.setCart(cart);

        cartService.addProductToCart(1L, 2, "Cliente um");


        productService.saveProduct(product);

        AddProductRequest addProductRequest = new AddProductRequest(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCart().getId());
        String jsonRequest = objectMapper.writeValueAsString(addProductRequest);
        mockMvc.perform((RequestBuilder) post("add/Product")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.valueOf(jsonRequest)))
                .andExpect(status().isCreated());

        cart = cartRepository.findById(cart.getId()).get();
        BigDecimal expectedTotal = product.getPrice().multiply(new BigDecimal(2));
        assertEquals(expectedTotal, cart.getCartPrice());

    }



}
