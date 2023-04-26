package com.ecommerce.polotech.service;


import com.ecommerce.polotech.model.Product;
import com.ecommerce.polotech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product){
        if (productRepository.findByName(product.getName()).isPresent()) {
            throw new RuntimeException("Product already exists");
        } else if (product.getName().isEmpty() || product.getDescription().isEmpty() || product.getPrice().equals(null)) {
            throw new RuntimeException("Product is not valid");
        } else {
            productRepository.save(product);
        }
    }

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return this.productRepository.findById(id);
    }

    public void updateProduct(Product outOfDateProduct) {

        Product updatedProduct = productRepository.findById(outOfDateProduct.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Product by ID %d", outOfDateProduct.getId())
                ));
        updatedProduct.setName(outOfDateProduct.getName());
        updatedProduct.setDescription(outOfDateProduct.getDescription());
        updatedProduct.setPrice(outOfDateProduct.getPrice());
        productRepository.save(updatedProduct);

    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }



}
