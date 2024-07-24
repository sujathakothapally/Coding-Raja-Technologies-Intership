public class Transaction {
    private String transactionId;
    private String accountNumber;
    private String type; // deposit, withdrawal, transfer
    private double amount;
    private String date;

    public Transaction(String transactionId, String accountNumber, String type, double amount, String date) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // Getters and other methods...
}
