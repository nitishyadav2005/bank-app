package service.impl;

import domain.Account;
import domain.Transaction;

import java.util.List;

public interface BankService {
    String openAccount(String name, String email, String accountType );
    List<Account> listAccounts();

    void deposit(String accountNumber, double amount, String note);

    void withdraw(String accountNumber, double amount, String withdrawal);

    void transfer(String from, String to, double amount, String transfer);

    List<Transaction> getStatement(String account);
}
