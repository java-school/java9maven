package pl.javaschool.java9maven.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javaschool.java9maven.dao.Customer;
import pl.javaschool.java9maven.dao.CustomerDao;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private static final String GIVEN_CUSTOMER_PESEL = "90051603345";
    private static final String NON_EXISTING_CUSTOMER_PESEL = "11111111111";
    private static final String ANY_PESEL = "22222222222";

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void shouldRetrieveExistingCustomerFromDatabase() throws CustomerNotFoundException {
        //given
        Customer givenCustomer = givenCustomerWithPesel(GIVEN_CUSTOMER_PESEL);
        givenCustomerDaoWillReturn(
                givenCustomer,
                givenCustomerWithPesel(ANY_PESEL)
        );
        //when
        Customer expectedCustomer = customerService.getCustomer(GIVEN_CUSTOMER_PESEL);
        //then
        assertThat(expectedCustomer).isEqualTo(givenCustomer);
    }

    @Test
    void shouldAddNewCustomerToDatabase() {
        //given
        Customer givenCustomer = givenCustomerWithPesel(GIVEN_CUSTOMER_PESEL);
        //when
        customerService.createNewCustomer(givenCustomer);
        //then
        assertThatCustomerDaoContainsOnly(givenCustomer);
    }


    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        //given
        givenCustomerDaoWillReturn(
                givenCustomerWithPesel(GIVEN_CUSTOMER_PESEL),
                givenCustomerWithPesel(ANY_PESEL)
        );
        //when
        Throwable thrown = catchThrowable(() -> customerService.getCustomer(NON_EXISTING_CUSTOMER_PESEL));
        //then
        assertThat(thrown).isInstanceOf(CustomerNotFoundException.class)
                .hasMessage("Customer not found");
    }

    private Customer givenCustomerWithPesel(String pesel) {
        return new Customer(pesel, "Jan", "Nowak", 27);
    }

    private void assertThatCustomerDaoContainsOnly(Customer givenCustomer) {
        verify(customerDao).addCustomer(givenCustomer);
    }

    private void givenCustomerDaoWillReturn(Customer... givenCustomer) {
        given(customerDao.getCustomers()).willReturn(asList(givenCustomer));
    }
}