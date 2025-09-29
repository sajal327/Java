import java.util.ArrayList;
import java.util.Scanner;

// Account class
class Account {
    private int accountNumber;
    private String holderName;
    private double balance;
    private ArrayList<String> transactions = new ArrayList<>();

    public Account(int accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        transactions.add("Account created with balance: " + initialBalance);
    }

    public int getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: " + amount + " | Balance: " + balance);
            System.out.println("Deposited " + amount + ". New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew: " + amount + " | Balance: " + balance);
            System.out.println("Withdrawn " + amount + ". New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds or invalid amount!");
        }
    }

    // Show transaction history
    public void showTransactions() {
        System.out.println("\n--- Transaction History for " + holderName + " ---");
        for (String t : transactions) {
            System.out.println(t);
        }
    }

    @Override
    public String toString() {
        return "Account No: " + accountNumber + " | Holder: " + holderName + " | Balance: " + balance;
    }
}

// Main BankApp
public class BankApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Account account;

    public static void main(String[] args) {
        System.out.println("===== Bank Account Simulation =====");

        // Create a default account for demo
        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();

        account = new Account(accNo, name, balance);

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. View Account Details");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Balance");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(account);
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 5:
                    account.showTransactions();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting Bank App!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void depositMoney() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    private static void withdrawMoney() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }
}
