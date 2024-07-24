import java.util.Scanner;

public class Main {
    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Example usage
        System.out.println("Welcome to the Online Banking System!");
        boolean running = true;

        while (running) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        System.out.println("Thank you for using the Online Banking System!");
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = new User(username, password);
        bank.addUser(user);
        System.out.println("Registration successful!");
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = bank.authenticate(username, password);
        if (user != null) {
            System.out.println("Login successful!");
            userMenu(user);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void userMenu(User user) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("1. Create Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Transfer Funds");
            System.out.println("4. View Transactions");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount(user);
                    break;
                case 2:
                    viewAccounts(user);
                    break;
                case 3:
                    transferFunds(user);
                    break;
                case 4:
                    viewTransactions(user);
                    break;
                case 5:
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void createAccount(User user) {
        System.out.print("Enter account type (savings/checking): ");
        String accountType = scanner.nextLine();
        String accountNumber = "AC" + (int)(Math.random() * 100000); // Simplified account number generation
        Account account = new Account(accountNumber, accountType);
        user.addAccount(account);
        System.out.println("Account created successfully! Account Number: " + accountNumber);
    }

    private static void viewAccounts(User user) {
        for (Account account : user.getAccounts()) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: " + account.getBalance() + ", Type: " + account.getAccountType());
        }
    }

    private static void transferFunds(User user) {
        System.out.print("Enter source account number: ");
        String sourceAccountNumber = scanner.nextLine();
        System.out.print("Enter destination account number: ");
        String destinationAccountNumber = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Account sourceAccount = null;
        Account destinationAccount = null;

        for (Account account : user.getAccounts()) {
            if (account.getAccountNumber().equals(sourceAccountNumber)) {
                sourceAccount = account;
            }
            if (account.getAccountNumber().equals(destinationAccountNumber)) {
                destinationAccount = account;
            }
        }

        if (sourceAccount != null && destinationAccount != null && sourceAccount.withdraw(amount)) {
            destinationAccount.deposit(amount);
            String transactionId = "TX" + (int)(Math.random() * 100000);
            String date = java.time.LocalDate.now().toString();
            Transaction transaction = new Transaction(transactionId, sourceAccountNumber, "transfer", amount, date);
            bank.addTransaction(transaction);
            System.out.println("Funds transferred successfully!");
        } else {
            System.out.println("Transfer failed. Please check account details and balance.");
        }
    }

    private static void viewTransactions(User user) {
        for (Account account : user.getAccounts()) {
            System.out.println("Transactions for Account: " + account.getAccountNumber());
            for (Transaction transaction : bank.getTransactions(account.getAccountNumber())) {
                System.out.println("Transaction ID: " + transaction.getTransactionId() + ", Type: " + transaction.getType() + ", Amount: " + transaction.getAmount() + ", Date: " + transaction.getDate());
            }
        }
    }
}
