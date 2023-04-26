package com.ecommerce.polotech;


import com.ecommerce.polotech.model.*;
import com.ecommerce.polotech.repository.CartRepository;
import com.ecommerce.polotech.repository.OrderItemRepository;
import com.ecommerce.polotech.repository.SaleRepository;
import com.ecommerce.polotech.service.CustomerService;
import com.ecommerce.polotech.service.ProductService;
import com.ecommerce.polotech.service.SaleService;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class VendaTesteIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SaleService saleService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    private SaleRepository saleRepository;

    @Test
    public void testarFinalizarVenda() throws Exception {
        // TODO
        // Cria produto
        Product product = new Product();
        product.setName("Produto 1");
        product.setDescription("Descrição do produto 1");
        product.setPrice(BigDecimal.valueOf(10));

        Customer customer = new Customer();
        customer.setName("Cliente Um");
        customer.setEmail("cliente01@mail.com");
        customerService.saveCustomer(customer);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setCartPrice(BigDecimal.valueOf(10));
        cart.setCartStatus("Aberto");
        cart.setOrderItems(orderItemRepository.findAll());
        cart.setSale((Sale) saleRepository.findAll());
        cartRepository.save(cart);

        AddProductRequest addProductRequest = new AddProductRequest(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCart().getId());

        AddSaleRequest addSaleRequest = new AddSaleRequest(
                cart.getCustomer().getId(),
                cart.getCartPrice(),
                cart.getStatus(),
                cart.getOrderItems(),
                cart.getSale().getId());

        String jsonRequest = objectMapper.writeValueAsString(addProductRequest);



        mockMvc.perform((RequestBuilder) post("/sale/update")
                        .contentType(MediaType.valueOf("application/json"))
                        .contentType(MediaType.valueOf(jsonRequest)))
                .andExpect(status().isCreated());





        Optional<Customer> updatedCustomer = customerService.getCustomerById(customer.getId());
        BigDecimal expectedBalance = BigDecimal.valueOf(40.0);
        assertEquals(expectedBalance, updatedCustomer.get().getBalance());


        Optional<Product> updatedProduct = productService.getProductById(product.getId());
        Integer expectedStock = 9;
        assertEquals(expectedStock, updatedProduct.get().getStock());


        List<Sale> sales = saleService.findByCostumer(customer);
        assertEquals(1, sales.size());
        Sale sale = sales.get(0);
        assertEquals(cart.getCartPrice(), sale.getTotalValue());
        assertEquals(customer, sale.getCustomer());
        assertEquals(1, sale.getOrderItems().size());
        OrderItem saleItem = sale.getOrderItems().get(0);
        assertEquals(product, saleItem.getProduct());
        assertEquals(1, saleItem.getQuantity());
        assertEquals(product.getPrice(), saleItem.getPrice());
    }






}
