package com.ecommerce.polotech.controller;


import com.ecommerce.polotech.model.Sale;
import com.ecommerce.polotech.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;
    @PostMapping("/save")
    public void createSale(Sale sale) {
        saleService.createSale(sale);
    }

    @PutMapping("/update")
    public void updateSale(Sale sale) {
        saleService.updateSale(sale);
    }
}
