package com.Bank.BankApplication;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class BankService {
	
	private final AccountRepository repo;

    public BankService(AccountRepository repo) {
        this.repo = repo;
    }

    public Account deposit(Long id, double amount) {
        Account acc = repo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        acc.setBalance(acc.getBalance() + amount);
        return repo.save(acc);
    }

    public Account withdraw(Long id, double amount) {
        Account acc = repo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (acc.getBalance() < amount) throw new RuntimeException("Insufficient balance");
        acc.setBalance(acc.getBalance() - amount);
        return repo.save(acc);
    }

    public double checkBalance(Long id) {
        Account acc = repo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return acc.getBalance();
    }

    public void transfer(Long fromId, Long toId, double amount) {
        withdraw(fromId, amount);
        deposit(toId, amount);
    }

    public List<Account> getAllAccounts() {
        return repo.findAll();
    }

    public Account createAccount(String holder, double initialBalance) {
        return repo.save(new Account(holder, initialBalance));
    }
}



