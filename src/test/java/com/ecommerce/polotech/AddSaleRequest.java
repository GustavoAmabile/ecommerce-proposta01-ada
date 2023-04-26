package com.ecommerce.polotech;


import com.ecommerce.polotech.model.Cart;
import com.ecommerce.polotech.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSaleRequest {

    private LocalDateTime saleDate;
    private BigDecimal totalValue;
    private BigDecimal shippingValue;
    private BigDecimal discountValue;
    private BigDecimal finalValue;
    private Cart cart;

    public AddSaleRequest(Long id, BigDecimal cartPrice, String status, List<OrderItem> orderItems, Long id1) {
    }
}
