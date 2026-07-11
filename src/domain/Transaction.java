package domain;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private String accountNumber;
    private double amount;
    private LocalDateTime timestamp;
    private String note;

    public Transaction(String id, String accountNumber, double amount, LocalDateTime timestamp, String note) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.timestamp = timestamp;
        this.note = note;
    }
}
