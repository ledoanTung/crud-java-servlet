package com.example.customerproject.service;

import com.example.customerproject.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements CustomerService{
    private static Map<Integer,Customer> customers;

    static {
        customers = new HashMap<>();
        customers.put(1, new Customer(1, "John", "john@gmail.vn", "Hanoi"));
        customers.put(2, new Customer(2, "Bill", "bill@gmail.vn", "Danang"));
        customers.put(3, new Customer(3, "Alex", "alex@gmail.vn", "Saigon"));
        customers.put(4, new Customer(4, "Adam", "adam@gmail.vn", "Beijin"));
        customers.put(5, new Customer(5, "Sophia", "sophia@gmail.vn", "Miami"));
        customers.put(6, new Customer(6, "Rose", "rose@gmail.vn", "Newyork"));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public Customer findById(int id) {
        return customers.get(id);
    }

    @Override
    public void update(int id, Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public void remote(int id) {
        customers.remove(id);
    }
}
