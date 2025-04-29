package colm;

import java.util.ArrayList;
import java.util.List;

public class AccountCheck {
    private double balance;
    private String accountNumber;
    private List<Transactions> transactionsHistory;
    private AccountUser owner;

    // Конструктор/
    public AccountCheck(String accountNumber, double balance, AccountUser owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionsHistory = new ArrayList<>();
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public AccountUser getOwner() {
        return owner;
    }

    public List<Transactions> getTransactionsHistory() {
        return transactionsHistory;
    }

    // Добавление суммы на баланс и добавление в список транзакций
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive!");
        }
        balance += amount;
        transactionsHistory.add(new Transactions("Deposit", amount));
    }

    // Снятие средств
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive!");
        }
        if (amount > balance) {
            return false; // Недостаточно средств
        }
        balance -= amount;
        transactionsHistory.add(new Transactions("Withdraw", amount));
        return true;
    }

    // Перевод средств на другой счет
    public boolean transfer(double amount, AccountCheck anotherAccount) {
        if (withdraw(amount)) {
            anotherAccount.deposit(amount);
            transactionsHistory.add(new Transactions("Transfer to " + anotherAccount.getAccountNumber(), amount));
            anotherAccount.transactionsHistory.add(new Transactions("Transfer from " + accountNumber, amount));
            return true;
        }
        return false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean transferFunds(AccountCheck targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            targetAccount.balance += amount;
            transactionsHistory.add(new Transactions("Transfer to " + targetAccount.getAccountNumber(), amount));
            targetAccount.transactionsHistory.add(new Transactions("Transfer from " + accountNumber, amount));
            return true;
        }
        return false;
    }
}