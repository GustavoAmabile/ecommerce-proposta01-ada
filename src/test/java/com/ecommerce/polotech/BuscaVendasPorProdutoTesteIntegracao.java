package com.ecommerce.polotech;


import com.ecommerce.polotech.model.Customer;
import com.ecommerce.polotech.model.OrderItem;
import com.ecommerce.polotech.model.Product;
import com.ecommerce.polotech.model.Sale;
import com.ecommerce.polotech.repository.SaleRepository;
import com.ecommerce.polotech.service.CustomerService;
import com.ecommerce.polotech.service.ProductService;
import com.ecommerce.polotech.service.SaleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class BuscaVendasPorProdutoTesteIntegracao {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SaleService saleService;

    @Test
    public void testarBuscarVendasClientes() throws Exception {
        Product product = new Product();
        product.setName("Produto 1");
        product.setDescription("Descrição do produto 1");
        product.setPrice(BigDecimal.valueOf(10.0));
        productService.saveProduct(product);

        Sale saleOne = new Sale();
        saleOne.setOrderItems((List<OrderItem>) product);
        saleOne.setFinalValue(BigDecimal.valueOf(100.0));
        saleService.createSale(saleOne);

        Sale saleTwo = new Sale();
        saleOne.setOrderItems((List<OrderItem>) product);
        saleOne.setFinalValue(BigDecimal.valueOf(200.0));
        saleService.createSale(saleTwo);

        List<Sale> sales = saleService.findByOrderItems(product);

        assertEquals(2, sales.size());
        assertEquals(saleOne.getOrderItems(), sales.get(0).getOrderItems());
        assertEquals(saleOne.getFinalValue(), sales.get(0).getFinalValue());
        assertEquals(saleTwo.getOrderItems(), sales.get(1).getOrderItems());
        assertEquals(saleTwo.getFinalValue(), sales.get(1).getFinalValue());
    }

}
