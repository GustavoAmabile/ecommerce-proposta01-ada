package com.ecommerce.polotech.service;

import com.ecommerce.polotech.model.Cart;
import com.ecommerce.polotech.model.OrderItem;
import com.ecommerce.polotech.model.Product;
import com.ecommerce.polotech.model.Sale;
import com.ecommerce.polotech.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private SaleRepository saleRepository;


    public void createCart(Cart cart, Sale sale) {
        cartRepository.save(cart);
        saleRepository.save(sale);

    }
    public void saveCustomerCart(Long idCart, Long idCustomer) {

        Cart cart = cartRepository.findById(idCart)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Cart by ID %d", idCart)
                ));

        cart.setCustomer(customerRepository.findById(idCustomer)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Customer by ID %d", idCustomer)
                )));

        cartRepository.save(cart);

    }

    public void addProductToCart(Long idProduct, int quantity, String customerName) {

        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Product by ID %d", idProduct)
                ));


        Cart cart = cartRepository.findByCustomerName(customerName)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Cart by Customer Name %s", customerName)
                ));

        OrderItem orderItem = cart.getOrderItems().stream()
                .filter(item -> item.getProduct().getId().equals(idProduct))
                .findFirst()
                .orElse(new OrderItem());

        orderItem.setQuantity(orderItem.getQuantity() + quantity);
        orderItem.setCart(cart);
        orderItem.setProduct(product);

        cart.getOrderItems().add(orderItem);
        cart.setCartPrice(cart.getCartPrice()
                .add(product.getPrice()
                        .multiply(BigDecimal.valueOf(quantity))));

        orderItemRepository.save(orderItem);


    }


    public Cart getCartbyId(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Cart by ID %d", id)
                ));
    }



    public Object showCarts() {
        return cartRepository.findAll();
    }
}
