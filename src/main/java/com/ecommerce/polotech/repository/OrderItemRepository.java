package com.ecommerce.polotech.repository;

import com.ecommerce.polotech.model.Cart;
import com.ecommerce.polotech.model.OrderItem;
import com.ecommerce.polotech.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByCart(Cart cart);

    Optional<OrderItem> findByProduct(Product product);
}
