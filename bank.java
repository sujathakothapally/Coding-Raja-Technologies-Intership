import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<User> users;
    private List<Transaction> transactions;

    public Bank() {
        users = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                return user;
            }
        }
        return null;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions(String accountNumber) {
        List<Transaction> accountTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAccountNumber().equals(accountNumber)) {
                accountTransactions.add(transaction);
            }
        }
        return accountTransactions;
    }
}
