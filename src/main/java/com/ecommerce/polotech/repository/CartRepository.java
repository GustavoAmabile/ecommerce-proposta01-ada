package com.ecommerce.polotech.repository;

import com.ecommerce.polotech.model.Cart;
import com.ecommerce.polotech.model.Customer;
import com.ecommerce.polotech.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByCustomerName(String customerName);
}
