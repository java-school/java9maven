package pl.javaschool.java9maven.service;

import pl.javaschool.java9maven.dao.CustomerDao;
import pl.javaschool.java9maven.dao.Customer;

public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void createNewCustomer(Customer customer){
        if (customer != null){
            customerDao.addCustomer(customer);
        }
    }

    public Customer getCustomer(String pesel) throws CustomerNotFoundException {
        for (Customer customer : customerDao.getCustomers()) {
            if (customer.getPesel().equals(pesel)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("Customer not found");
    }
}
