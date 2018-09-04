package pl.javaschool.java9maven.dao;

import java.util.Objects;

public class Customer {
    private final String pesel;
    private final String firstName;
    private final String lastName;
    private final int age;

    public Customer(String pesel, String firstName, String lastName, int age) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPesel() {
        return pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age &&
                Objects.equals(pesel, customer.pesel) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel, firstName, lastName, age);
    }
}
