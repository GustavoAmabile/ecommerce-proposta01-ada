package com.ecommerce.polotech.controller;


import com.ecommerce.polotech.model.Customer;
import com.ecommerce.polotech.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public void createCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
    }

    @GetMapping("/all")
    public List<Customer> showAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer showCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id).get();
    }

    @PutMapping("/update")
    public void updateOutDatedCustomer(@RequestBody Customer customer){
        customerService.updateCustomer(customer);
    }

    @DeleteMapping("/delete/{id}")
    public void removeCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }

}
