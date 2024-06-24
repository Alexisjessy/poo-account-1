package fr.afpa.account;

import java.time.LocalDate;

/**
 * Classe principale du projet, contient la fonction "main"
 */
class AccountMain {
    public static void main(String[] args) {
        System.out.println("\n----- Exercice de programmation objet - classes \"Account\" & \"Customer\" -----");

        // Instancier des objets de la classe "Account"
        Account account1 = new Account("FR123456789", 500, 200);
        Account account2 = new Account("FR987654321", 300, 0);

        account1.addMoney(100);
        account2.removeMoney(400);
        account1.transfer(account2, 300);

        Account account = new Account();
        String iban1 = "FR1420041010050500013M02606";
        String iban2 = "7630006000011234567890189";
        String iban3 = "DE89370400440532013000";

        System.out.println("IBAN " + iban1 + " is valid: " + account.checkIban(iban1));
        System.out.println("IBAN " + iban2 + " is valid: " + account.checkIban(iban2));
        System.out.println("IBAN " + iban3 + " is valid: " + account.checkIban(iban3));

        // Instancier des objets de la classe "Customer"
        Customer client1 = new Customer(12, "Dupont", "Jean", LocalDate.of(1990, 5, 15));
        Customer client2 = new Customer(11, "Martin", "Paul", LocalDate.of(1985, 8, 20));

        // Ajouter des objets "Account" à des "Customer"
        client1.addAccount(account1);
        client2.addAccount(account2);

        // Afficher les informations des clients
        System.out.println(client1);
        System.out.println(client2);

        // Tester la suppression d'un compte
        client2.removeAccount(account2);
        System.out.println("Après suppression d'un compte:");
        System.out.println(client2);

        // Afficher tous les clients
        Customer.displayAllCustomers();
    }
}
