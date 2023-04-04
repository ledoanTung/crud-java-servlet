package com.example.customerproject.service;

import com.example.customerproject.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    void  save (Customer customer);
    Customer findById(int id);
    void update (int id, Customer customer);
    void remote (int id);

}
