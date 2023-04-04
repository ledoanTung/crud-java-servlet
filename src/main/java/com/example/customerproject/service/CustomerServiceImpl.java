package com.example.customerproject.service;

import com.example.customerproject.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements CustomerService{
    private String jdbcURL = "jdbc:mysql://localhost:3306/dbtest?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";
    private static final String INSERT_CUSTOMER_SQL
            = "insert into customer (name, email, address) values (?,?,?);";
    private static final String SELECT_CUSTOMER_BY_ID
            = "select name,email,address from customer where id = ?";
    private static final String SELECT_ALL_CUSTOMER
            ="select * from customer";
    private static final String DELETE_CUSTOMER
            ="delete from customer where id = ?;";
    private static final String UPDATE_CUSTOMER
            ="update customer set name =?, email = ?, address = ? where id = ?;";

    protected Connection getConnect() throws ClassNotFoundException, SQLException {
        Connection connection = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        return connection;
    }

    @Override
    public void insert(Customer customer) {
        Connection connection = null;
        try {
            connection = getConnect();
            PreparedStatement preparedStatement =connection.prepareStatement(INSERT_CUSTOMER_SQL);
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getEmail());
            preparedStatement.setString(3,customer.getAddress());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer selectById(int id) {
        Customer customer = null;
        try {
            Connection connection = getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                customer = new Customer(id, name, email, address);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection connection = getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id =  resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                customers.add(new Customer(id,name,email,address));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public boolean delete(int id) {
        boolean check;
        try {
            Connection connection = getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER);
            preparedStatement.setInt(1,id);
            check = preparedStatement.executeUpdate() >0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    @Override
    public boolean update(Customer customer) {
        boolean check ;
        try {
            Connection connection = getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setInt(4, customer.getId());
            check = preparedStatement.executeUpdate()> 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

}
