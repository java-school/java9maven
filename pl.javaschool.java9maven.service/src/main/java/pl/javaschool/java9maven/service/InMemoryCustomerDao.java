package pl.javaschool.java9maven.service;

import pl.javaschool.java9maven.dao.CustomerDao;
import pl.javaschool.java9maven.dao.Customer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InMemoryCustomerDao implements CustomerDao {
    private final Set<Customer> customers;

    private static CustomerDao instance;

    private InMemoryCustomerDao() {
        customers = new HashSet<>();
    }

    public static CustomerDao getCustomerDao() {
        if (instance == null) {
            instance = new InMemoryCustomerDao();
        }
        return instance;
    }

    @Override
    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        return customers.remove(customer);
    }
}
