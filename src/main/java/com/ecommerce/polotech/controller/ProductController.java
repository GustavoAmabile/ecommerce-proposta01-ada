package com.ecommerce.polotech.controller;


import com.ecommerce.polotech.model.Product;
import com.ecommerce.polotech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public void createProduct(@RequestBody Product product){
        productService.saveProduct(product);
    }

    @GetMapping("/all")
    public List<Product> showAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product showProductById(@PathVariable Long id){
        return productService.getProductById(id).get();
    }

    @PutMapping("/update")
    public void updateOutDatedProduct(@RequestBody Product product){
        productService.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public void removeProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
