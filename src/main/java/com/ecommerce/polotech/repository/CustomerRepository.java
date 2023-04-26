package com.ecommerce.polotech.repository;

import com.ecommerce.polotech.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Object> findByEmail(String email);
}
