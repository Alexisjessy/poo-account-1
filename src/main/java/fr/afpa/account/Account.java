package fr.afpa.account;

import java.math.BigInteger;

/**
 * Classe représentant un compte bancaire
 */
public class Account {

    private String internationalBankAccountNumber;
    private int balance;
    private int overdraftAuthorization;
    private double amount;

    public Account(String internationalBankAccountNumber, int balance, int overdraftAuthorization) {
        this.internationalBankAccountNumber = internationalBankAccountNumber;
        this.balance = balance;
        this.overdraftAuthorization = overdraftAuthorization;
    }

    // Ajoutez un constructeur sans argument si nécessaire
    public Account() {
    }

    public String getInternationalBankAccountNumber() {
        return internationalBankAccountNumber;
    }

    public void setInternationalBankAccountNumber(String internationalBankAccountNumber) {
        this.internationalBankAccountNumber = internationalBankAccountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getOverdraftAuthorization() {
        return overdraftAuthorization;
    }

    public void setOverdraftAuthorization(int overdraftAuthorization) {
        this.overdraftAuthorization = overdraftAuthorization;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "internationalBankAccountNumber='" + internationalBankAccountNumber + '\'' +
                ", balance=" + balance +
                ", overdraftAuthorization=" + overdraftAuthorization +
                '}';
    }

    // Méthode pour ajouter de l'argent sur le compte
    public void addMoney(int amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Added " + amount + " to the account. New balance: " + this.balance);
        } else {
            System.out.println("The amount to be added must be positive.");
        }
    }

    // Méthode pour retirer de l'argent du compte
    public void removeMoney(int amount) {
        if (amount > 0) {
            if (this.balance - amount >= -this.overdraftAuthorization) {
                this.balance -= amount;
                System.out.println("Removed " + amount + " from the account. New balance: " + this.balance);
            } else {
                System.out.println("Insufficient funds! Cannot withdraw " + amount);
            }
        } else {
            System.out.println("The amount to be removed must be positive.");
        }
    }

    // Méthode pour transférer de l'argent vers un autre compte
    public void transfer(Account targetAccount, int amount) {
        if (amount > 0) {
            if (this.balance - amount >= -this.overdraftAuthorization) {
                this.balance -= amount;
                targetAccount.addMoney(amount);
                System.out.println("Transferred " + amount + " to " + targetAccount.getInternationalBankAccountNumber()
                        + ". New balance: " + this.balance);
            } else {
                System.out.println("Insufficient funds! Cannot transfer " + amount);
            }
        } else {
            System.out.println("The amount to be transferred must be positive.");
        }
    }

    // Méthode de vérification de l'IBAN
    public boolean checkIban(String iban) {
        int IBAN_MIN_SIZE = 15;
        int IBAN_MAX_SIZE = 34;
        BigInteger IBAN_MODULUS = BigInteger.valueOf(97);

        String trimmed = iban.trim();
        if (trimmed.length() < IBAN_MIN_SIZE || trimmed.length() > IBAN_MAX_SIZE) {
            return false;
        }

        // Réarranger l'IBAN
        String reformat = trimmed.substring(4) + trimmed.substring(0, 4);

        // Convertir les lettres en chiffres
        StringBuilder numericIban = new StringBuilder();
        for (int i = 0; i < reformat.length(); i++) {
            int charValue = Character.getNumericValue(reformat.charAt(i));
            if (charValue < 0 || charValue > 35) {

                return false;
            }
            numericIban.append(charValue);
        }

        // Convertir la chaîne en nombre et vérifier le modulo
        BigInteger ibanNumber = new BigInteger(numericIban.toString());
        return ibanNumber.mod(IBAN_MODULUS).intValue() == 1;
    }
}