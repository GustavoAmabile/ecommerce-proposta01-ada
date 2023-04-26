package com.ecommerce.polotech;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private Long cartId;
}
