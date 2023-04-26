package com.ecommerce.polotech.controller;


import com.ecommerce.polotech.model.Cart;
import com.ecommerce.polotech.model.Sale;
import com.ecommerce.polotech.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/save")
    public ResponseEntity<Cart> createCart(Cart cart, Sale sale) {
        cartService.createCart(cart, sale);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/saveCustomerCart")
    public ResponseEntity<Cart> saveCustomerCart(@RequestParam Long idCart, @RequestParam Long idCustomer) {
        cartService.saveCustomerCart(idCart, idCustomer);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Cart> addProductToCart(@RequestParam Long idProduct, @RequestParam int quantity, @RequestParam String customerName) {
        cartService.addProductToCart(idProduct, quantity, customerName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getCart")
    public ResponseEntity showCarts() {
        return ResponseEntity.ok(cartService.showCarts());
    }

    @GetMapping("/getCart/{id}")
    public ResponseEntity showCartById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCartbyId(id));
    }
}
