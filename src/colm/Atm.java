package colm;

public class Atm {
    private Bank bank;

    public Atm(Bank bank) {
        this.bank = bank;
    }
    //
    public AccountCheck login(String accountNumber, String password) {
        AccountCheck accountCheck = bank.getAccountCheck(accountNumber);
        if (accountCheck != null && accountCheck.getOwner().authenticate(password)) {
            return accountCheck;
        }
        return null;
    }

    public void showTransactions(AccountCheck accountCheck) {
        for(Transactions transaction : accountCheck.getTransactionsHistory()) {
            System.out.println(transaction);
        }
    }

}
