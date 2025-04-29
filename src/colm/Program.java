package colm;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        // Создаем банк и добавляем тестовый аккаунт
        Bank bank = new Bank();
        AccountUser owner = new AccountUser(10001, "Nikita", "1234");
        AccountUser owner2 = new AccountUser(10002, "Valera", "1234");
        AccountCheck account = new AccountCheck("10001", 1000, owner);
        AccountCheck account2 = new AccountCheck("10002", 5000, owner2);
        bank.addAccountCheck(account);
        bank.addAccountCheck(account2);

        // Создаем GUI
        JFrame frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Account Number:"));
        JTextField accountField = new JTextField();
        panel.add(accountField);

        panel.add(new JLabel("PIN Code:"));
        JTextField pinField = new JTextField();
        panel.add(pinField);

        JButton loginButton = new JButton("Login");
        panel.add(loginButton);
        //
        frame.add(panel);
        frame.setVisible(true);

        // Обработка нажатия кнопки "Login"
        loginButton.addActionListener(e -> {
            String enteredAccountNumber = accountField.getText();
            String enteredPinCode = pinField.getText();

            // Проверка на пустые значения
            if (enteredAccountNumber.trim().isEmpty() || enteredPinCode.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter valid account number and PIN code!");
                return;
            }

            // Поиск аккаунта
            AccountCheck foundAccount = bank.getAccountCheck(enteredAccountNumber);
            if (foundAccount == null) {
                JOptionPane.showMessageDialog(frame, "Account not found!");
                return;
            }

            // Проверка PIN-кода
            if (!foundAccount.getOwner().authenticate(enteredPinCode)) {
                JOptionPane.showMessageDialog(frame, "Invalid PIN code!");
                return;
            }

            // Успешный вход
            JOptionPane.showMessageDialog(frame, "Login successful!");

            // Создаем новое окно с информацией о пользователе
            JFrame userInfoFrame = new JFrame("User Information");
            userInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            userInfoFrame.setSize(400, 300);

            // Панель для отображения информации
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new GridLayout(6, 1));

            // Добавляем информацию о пользователе
            infoPanel.add(new JLabel("Account Number: " + foundAccount.getAccountNumber()));
            infoPanel.add(new JLabel("Owner Name: " + foundAccount.getOwner().getName()));
            infoPanel.add(new JLabel("Balance: $" + foundAccount.getBalance()));

            // Кнопка "Exit Account"
            JButton exitButton = new JButton("Exit Account");
            exitButton.addActionListener(ev -> userInfoFrame.dispose());
            infoPanel.add(exitButton);

            // Кнопка "Transaction History"
            JButton transactionHistoryButton = new JButton("Transaction History");
            transactionHistoryButton.addActionListener(ev -> {
                StringBuilder transactionList = new StringBuilder("Transaction History:\n");
                for (Transactions transaction : foundAccount.getTransactionsHistory()) {
                    transactionList.append(transaction.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(userInfoFrame, transactionList.toString());
            });
            infoPanel.add(transactionHistoryButton);

            // Кнопка "Transfer Funds"
            JButton transferFundsButton = new JButton("Transfer Funds");
            transferFundsButton.addActionListener(ev -> {
                String targetAccountNumber = JOptionPane.showInputDialog(userInfoFrame, "Enter target account number:");
                String amountStr = JOptionPane.showInputDialog(userInfoFrame, "Enter amount to transfer:");
                try {
                    double amount = Double.parseDouble(amountStr);

                    // Проверка на отрицательные значения
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(userInfoFrame, "Amount must be positive!");
                        return;
                    }

                    AccountCheck targetAccount = bank.getAccountCheck(targetAccountNumber);
                    if (targetAccount == null) {
                        JOptionPane.showMessageDialog(userInfoFrame, "Target account not found!");
                        return;
                    }
                    if (foundAccount.transferFunds(targetAccount, amount)) {
                        JOptionPane.showMessageDialog(userInfoFrame, "Transfer successful!");
                    } else {
                        JOptionPane.showMessageDialog(userInfoFrame, "Insufficient funds!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(userInfoFrame, "Invalid amount entered!");
                }
            });
            infoPanel.add(transferFundsButton);

            userInfoFrame.add(infoPanel);
            userInfoFrame.setVisible(true);
        });
    }
}