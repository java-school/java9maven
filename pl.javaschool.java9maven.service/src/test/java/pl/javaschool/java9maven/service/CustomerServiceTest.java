package pl.javaschool.java9maven.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.javaschool.java9maven.dao.CustomerDao;
import pl.javaschool.java9maven.dao.Customer;

public class CustomerServiceTest {

    private static final String GIVEN_CUSTOMER_PESEL = "90051603345";
    private static final String NON_EXISTING_CUSTOMER_PESEL = "11111111111";
    private CustomerDao customerDao;

    private CustomerService customerService;

    @Before
    public void setUp() {
        customerDao = InMemoryCustomerDao.getCustomerDao();
        customerService = new CustomerService(customerDao);
    }

    @Test
    public void shouldAddNewCustomerToDatabase() {
        Customer customer = givenCustomer();
        customerService.createNewCustomer(customer);
        Assert.assertTrue(customerDao.getCustomers().contains(customer));
    }

    @Test
    public void shouldRetrieveExistingCustomerFromDatabase() throws CustomerNotFoundException {
        Customer givenCustomer = givenCustomer();
        customerDao.addCustomer(givenCustomer);

        Customer customer = customerService.getCustomer(GIVEN_CUSTOMER_PESEL);
        Assert.assertEquals(givenCustomer, customer);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void shouldThrowExceptionWhenCustomerNotFound() throws CustomerNotFoundException {
        customerService.getCustomer(NON_EXISTING_CUSTOMER_PESEL);
    }

    private Customer givenCustomer() {
        return new Customer(GIVEN_CUSTOMER_PESEL, "Jan", "Nowak", 27);
    }
}