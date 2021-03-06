package pl.javaschool.java9maven.dao;

import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomers();
    void addCustomer(Customer customer);
    boolean deleteCustomer(Customer customer);
}
