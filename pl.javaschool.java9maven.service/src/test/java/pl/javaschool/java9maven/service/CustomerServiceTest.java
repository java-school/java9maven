package pl.javaschool.java9maven.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.javaschool.java9maven.dao.Customer;
import pl.javaschool.java9maven.dao.CustomerDao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThat(customerDao.getCustomers())
                .containsOnly(customer);
    }

    @Test
    void shouldRetrieveExistingCustomerFromDatabase() throws CustomerNotFoundException {
        Customer givenCustomer = givenCustomer();
        customerDao.addCustomer(givenCustomer);

        Customer customer = customerService.getCustomer(GIVEN_CUSTOMER_PESEL);
        assertThat(customer).isEqualTo(givenCustomer);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        assertThatThrownBy(() -> customerService.getCustomer(NON_EXISTING_CUSTOMER_PESEL))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessage("Customer not found");
    }

    private Customer givenCustomer() {
        return new Customer(GIVEN_CUSTOMER_PESEL, "Jan", "Nowak", 27);
    }
}