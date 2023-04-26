package com.ecommerce.polotech.service;


import com.ecommerce.polotech.model.Cart;
import com.ecommerce.polotech.model.Customer;
import com.ecommerce.polotech.model.OrderItem;
import com.ecommerce.polotech.model.Product;
import com.ecommerce.polotech.repository.CartRepository;
import com.ecommerce.polotech.repository.OrderItemRepository;
import com.ecommerce.polotech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrdemItemService {

@Autowired
private OrderItemRepository orderItemRepository;

public void createOrderItem(Long idProduct, int quantity) {

    ProductRepository productRepository = null;
    CartRepository cartRepository = null;

    Product product = productRepository.findById(idProduct)
            .orElseThrow(() -> new RuntimeException(
                    String.format("Cannot Find Product by ID %d", idProduct)
            ));

    OrderItem orderItem = new OrderItem();
    orderItem.setProduct(product);
    orderItem.setQuantity(quantity);
    orderItem.setPrice(product.getPrice().multiply(new BigDecimal(quantity)));

    orderItemRepository.save(orderItem);


    Cart cart = cartRepository.findById(idProduct)
            .orElseThrow(() -> new RuntimeException(
                    String.format("Cannot Find Cart %d", idProduct)
            ));

    cart.getOrderItems().add(orderItem);

    cartRepository.save(cart);


}
}
