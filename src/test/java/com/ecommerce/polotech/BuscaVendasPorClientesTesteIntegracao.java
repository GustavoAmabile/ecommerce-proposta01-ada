package com.ecommerce.polotech;


import com.ecommerce.polotech.model.Customer;
import com.ecommerce.polotech.model.Sale;
import com.ecommerce.polotech.repository.SaleRepository;
import com.ecommerce.polotech.service.CustomerService;
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
public class BuscaVendasPorClientesTesteIntegracao {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SaleService saleService;

    @Test
    public void testarBuscarVendasClientes() throws Exception {
        Customer customer = new Customer();
        customer.setName("João");
        customer.setEmail("joao@mail.com");
        customerService.saveCustomer(customer);

        Sale saleOne = new Sale();
        saleOne.setCustomer(customer);
        saleOne.setFinalValue(BigDecimal.valueOf(10.0));
       saleService.createSale(saleOne);

        Sale saleTwo = new Sale();
        saleTwo.setCustomer(customer);
        saleTwo.setFinalValue(BigDecimal.valueOf(20.0));
        saleService.createSale(saleTwo);

        List<Sale> sales = saleService.findByCostumer(customer);

        assertEquals(2, sales.size());
        assertEquals(saleOne.getCustomer(), sales.get(0).getCustomer());
        assertEquals(saleOne.getFinalValue(), sales.get(0).getFinalValue());
        assertEquals(saleTwo.getCustomer(), sales.get(1).getCustomer());
        assertEquals(saleTwo.getFinalValue(), sales.get(1).getFinalValue());



    }
}
