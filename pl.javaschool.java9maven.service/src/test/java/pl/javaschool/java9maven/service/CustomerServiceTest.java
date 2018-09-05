package pl.javaschool.java9maven.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.javaschool.java9maven.dao.Customer;
import pl.javaschool.java9maven.dao.CustomerDao;

class CustomerServiceTest {

    private static final String GIVEN_CUSTOMER_PESEL = "90051603345";
    private static final String NON_EXISTING_CUSTOMER_PESEL = "11111111111";
    private CustomerDao customerDao;

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerDao = InMemoryCustomerDao.getCustomerDao();
        customerService = new CustomerService(customerDao);
    }

    @Test
    void shouldAddNewCustomerToDatabase() {
        Customer customer = givenCustomer();
        customerService.createNewCustomer(customer);
        Assertions.assertTrue(customerDao.getCustomers().contains(customer));
    }

    @Test
    void shouldRetrieveExistingCustomerFromDatabase() throws CustomerNotFoundException {
        Customer givenCustomer = givenCustomer();
        customerDao.addCustomer(givenCustomer);

        Customer customer = customerService.getCustomer(GIVEN_CUSTOMER_PESEL);
        Assertions.assertEquals(givenCustomer, customer);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        Assertions.assertThrows(
                CustomerNotFoundException.class,
                () -> customerService.getCustomer(NON_EXISTING_CUSTOMER_PESEL)
        );

    }

    private Customer givenCustomer() {
        return new Customer(GIVEN_CUSTOMER_PESEL, "Jan", "Nowak", 27);
    }
}