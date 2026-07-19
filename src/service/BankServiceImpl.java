package service;

import domain.Account;
import domain.Customer;
import domain.Transaction;
import domain.Type;
import repository.CustomerRepository;
import repository.TransactionRepository;
import service.impl.BankService;
import repository.AccountRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {
    private final AccountRepository accountRepository = new AccountRepository();
    private final TransactionRepository transactionRepository = new TransactionRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();
    @Override
    public String openAccount(String name, String email, String accountType) {
        String customerId = UUID.randomUUID().toString();

        // create customer
        Customer c = new Customer(email, customerId, name);
        customerRepository.save(c);

        // CHANGE LATER --> 10+1 = AC11
//        String accountNumber = UUID.randomUUID().toString();
        String accountNumber = getAccountNumber();
        Account account = new Account(accountNumber, customerId, (double) 0,accountType );
        accountRepository.save(account);

        return accountNumber;
    }

    @Override
    public List<Account> listAccounts() {
        return accountRepository.findAll().stream()
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }

    @Override
    public void deposit(String accountNumber, double amount, String note) {
        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));
        account.setBalance(account.getBalance() + amount);
        Transaction transaction = new Transaction(account.getAccountNumber(),
                amount, UUID.randomUUID().toString(), note, LocalDateTime.now(), Type.DEPOSIT );
        transactionRepository.add(transaction);


    }

    @Override
    public void withdraw(String accountNumber, double amount, String note) {
        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));
        if(account.getBalance().compareTo(amount) < 0)
            throw new RuntimeException("Insufficient Balance");
        account.setBalance(account.getBalance() - amount);
        Transaction transaction = new Transaction(account.getAccountNumber(),
                amount, UUID.randomUUID().toString(), note, LocalDateTime.now(), Type.WITHDRAW );
        transactionRepository.add(transaction);
    }

    @Override
    public void transfer(String fromAcc, String toAcc, double amount, String note) {
        if(fromAcc.equals(toAcc))
            throw new RuntimeException("Cannot transfer to your own account");
        Account from = accountRepository.findByNumber(fromAcc)
                .orElseThrow(() -> new RuntimeException("Account not found: " + fromAcc));
        Account to = accountRepository.findByNumber(toAcc)
                .orElseThrow(() -> new RuntimeException("Account not found: " + toAcc));
        if(from.getBalance().compareTo(amount) < 0)
            throw new RuntimeException("Insufficient Balance");

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        transactionRepository.add( new Transaction(from.getAccountNumber(),
                amount, UUID.randomUUID().toString(),
                note, LocalDateTime.now(), Type.TRANSFER_OUT));

        transactionRepository.add( new Transaction(to.getAccountNumber(),
                amount, UUID.randomUUID().toString(),
                note, LocalDateTime.now(), Type.TRANSFER_IN));
    }

    @Override
    public List<Transaction> getStatement(String account) {
        return transactionRepository.findByAccount(account).stream()
                .sorted(Comparator.comparing(Transaction::getTimestamp))
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> searchAccountsByCustomerName(String q) {
        String query = (q == null) ? " " : q.toLowerCase();
//        List<Account> result = new ArrayList<>();
//        for(Customer c : customerRepository.findAll()){
//             if(c.getName().toLowerCase().contains(query))
//                 result.addAll(accountRepository.findByCustomerId(c.getId()));
//        }
//        result.sort(Comparator.comparing(Account::getAccountNumber));
//        return result;

        return customerRepository.findAll().stream()
                .filter(c -> c.getName().toLowerCase().contains(query))
                .flatMap(c -> accountRepository.findByCustomerId(c.getId()).stream())
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }

    private String getAccountNumber() {
        int size = accountRepository.findAll().size() + 1;
        return String.format("AC%06d", size);
    }
}
