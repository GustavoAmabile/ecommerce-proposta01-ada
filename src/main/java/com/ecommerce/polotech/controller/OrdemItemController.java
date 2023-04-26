package com.ecommerce.polotech.controller;


import com.ecommerce.polotech.model.OrderItem;
import com.ecommerce.polotech.service.OrdemItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderItem")
public class OrdemItemController {

    @Autowired
    private OrdemItemService ordemItemService;
    @PostMapping("/saveOrderItem")
    public void saveOrderItem(@RequestBody Long idProduct, @RequestBody int quantity) {
        ordemItemService.createOrderItem(idProduct, quantity);

    }
}
