package com.example.bookstore;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final List<Customer> customerList = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
        customerList.add(customer);
        return customer;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerList;
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerList.stream()
                           .filter(customer -> customer.getId().equals(id))
                           .findFirst()
                           .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer updatedCustomer) {
        Customer customer = getCustomerById(id);
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPassword(updatedCustomer.getPassword());
        return customer;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        Customer customer = getCustomerById(id);
        customerList.remove(customer);
    }
}
