package com.example.customerproject.controller;

import com.example.customerproject.model.Customer;
import com.example.customerproject.service.CustomerService;
import com.example.customerproject.service.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action){
            case "create":
                formCreateCustomer(req,resp);
                break;
            case "edit":
                formEditCustomer(req,resp);
                break;
            case "delete":
                formDeleteCustomer(req,resp);
                break;
            case "view":
                viewCustomer(req,resp);
                break;
            default:
                listCustomers(req,resp);
                break;


        }

    }

    private void viewCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = this.customerService.selectById(id);
        RequestDispatcher requestDispatcher;

        if (customer == null){
            requestDispatcher = req.getRequestDispatcher("/error.jsp");
        }else {
            req.setAttribute("customer", customer);
            requestDispatcher = req.getRequestDispatcher("customer/view.jsp");
        }
        requestDispatcher.forward(req,resp);
    }

    private void formDeleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = this.customerService.selectById(id);

        RequestDispatcher requestDispatcher;

        if (customer == null){
            requestDispatcher = req.getRequestDispatcher("error.jsp");
        }else {
            requestDispatcher = req.getRequestDispatcher("customer/delete.jsp");
            req.setAttribute("customer",customer);
        }
        requestDispatcher.forward(req,resp);
    }

    private void formEditCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = this.customerService.selectById(id);
        System.out.println("++++++++++++"+customer) ;
        RequestDispatcher requestDispatcher;
        if (customer == null){
            requestDispatcher = req.getRequestDispatcher("error.jsp");
        }else {
            req.setAttribute("customer", customer);
            requestDispatcher = req.getRequestDispatcher("customer/edit.jsp");

        }
        requestDispatcher.forward(req,resp);
    }

    private void formCreateCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("customer/create.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void listCustomers(HttpServletRequest req, HttpServletResponse resp) {
        List<Customer> customers = this.customerService.findAll();
        req.setAttribute("customers", customers);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("customer/list.jsp");
        try {
            requestDispatcher.forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action){
            case "create":
                createCustomer(req,resp);
                break;
            case "edit":
                editCustomer(req,resp);
                break;
            case "delete":
                deleteCustomer(req,resp);
                break;
            default:
                break;
        }

    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = this.customerService.selectById(id);

        RequestDispatcher requestDispatcher;

        if (customer == null){
            requestDispatcher = req.getRequestDispatcher("error.jsp");
        }else {
            this.customerService.delete(id);
            resp.sendRedirect("/customers");
        }


    }

    private void editCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        Customer customer = this.customerService.selectById(id);
        System.out.println("__________" +customer);
        RequestDispatcher requestDispatcher;

        if (customer == null){
            requestDispatcher = req.getRequestDispatcher("/error.jsp");
        }else {
            customer.setName(name);
            customer.setEmail(email);
            customer.setAddress(address);
            this.customerService.update(customer);
            req.setAttribute("customer", customer);
            req.setAttribute(
                    "message", "Customer information was updated"
            );
            requestDispatcher = req.getRequestDispatcher("/customer/edit.jsp");

        }

        requestDispatcher.forward(req,resp);
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        Customer customer = new Customer(name,email,address);

        this.customerService.insert(customer);
        RequestDispatcher requestDispatcher
                = req.getRequestDispatcher("/customer/create.jsp");
        req.setAttribute("message","Create new customer success");
        requestDispatcher.forward(req,resp);
    }

}


