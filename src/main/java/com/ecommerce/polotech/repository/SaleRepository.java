package com.ecommerce.polotech.repository;

import com.ecommerce.polotech.model.Customer;
import com.ecommerce.polotech.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
