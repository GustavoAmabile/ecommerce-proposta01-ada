package com.ecommerce.polotech.service;


import com.ecommerce.polotech.model.Customer;
import com.ecommerce.polotech.model.Product;
import com.ecommerce.polotech.model.Sale;
import com.ecommerce.polotech.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public void createSale(Sale sale) {
        if (sale.getCart() == null) {
            throw new RuntimeException("Cart is null");
        } else {
            saleRepository.save(sale);
        }

    }

    public void updateSale(Sale sale) {
        Sale updatedSale = saleRepository.findById(sale.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Sale by ID %d", sale.getId())
                ));
        updatedSale.setCart(sale.getCart());
        updatedSale.setFinalValue(sale.getFinalValue());
        saleRepository.save(updatedSale);

    }

    public List<Sale> findByCostumer(Customer customer) {
        saleRepository.findById(customer.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Sale by ID %d", customer.getId())
                ));
        return saleRepository.findAll();
    }


    public List<Sale> findByOrderItems(Product product) {
        saleRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Sale by ID %d", product.getId())
                ));
        return saleRepository.findAll();
    }
}
