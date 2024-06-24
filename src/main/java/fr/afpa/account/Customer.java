package fr.afpa.account;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe qui représente un client, propriétaire de comptes bancaires
 */
public class Customer {
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private ArrayList<Account> accounts = new ArrayList<>();

    private static ArrayList<Customer> customersList = new ArrayList<>();

    public Customer(String lastName, String firstName, LocalDate birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;

        customersList.add(this);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", accounts=" + accounts +
                '}';
    }

    public static void displayAllCustomers() {
        for (Customer customer : customersList) {
            System.out.println(customer);
        }
    }
}
