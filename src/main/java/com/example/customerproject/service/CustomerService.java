package com.example.customerproject.service;

import com.example.customerproject.model.Customer;

import java.util.List;

public interface CustomerService {

    public void insert (Customer customer);
    public  Customer selectById (int id);

    public List <Customer> findAll();

    public boolean delete(int id);

    public boolean update (Customer customer);

}
