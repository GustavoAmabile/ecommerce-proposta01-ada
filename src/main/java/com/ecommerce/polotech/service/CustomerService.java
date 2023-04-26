package com.ecommerce.polotech.service;


import com.ecommerce.polotech.model.Customer;
import com.ecommerce.polotech.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new RuntimeException("Customer already exists");
        } else if (customer.getEmail().isEmpty() || customer.getName().isEmpty()) {
            throw new RuntimeException("Customer is not valid");
        } else {
            customerRepository.save(customer);
        }
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return this.customerRepository.findById(id);
    }

    public void updateCustomer(Customer outOfDateCustomer) {

        Customer updatedCustomer = customerRepository.findById(outOfDateCustomer.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Customer by ID %d", outOfDateCustomer.getId())
                ));
        updatedCustomer.setName(outOfDateCustomer.getName());
        updatedCustomer.setEmail(outOfDateCustomer.getEmail());
        customerRepository.save(updatedCustomer);

    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
