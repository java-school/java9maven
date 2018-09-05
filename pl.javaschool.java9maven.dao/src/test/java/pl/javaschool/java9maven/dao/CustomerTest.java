package pl.javaschool.java9maven.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {
    private static final String PESEL = "90050688765";
    private static final String FIRST_NAME = "Jan";
    private static final String LAST_NAME = "Nowak";
    private static final int AGE = 25;
    private Customer customer;

    @BeforeEach
    void beforeEach() {
        customer = new Customer(PESEL, FIRST_NAME, LAST_NAME, AGE);
    }

    @Test
    void dummyTest() {
        assertThat(customer.getFirstName()).isEqualTo(FIRST_NAME);
    }

}