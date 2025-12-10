package com.Bank.BankApplication;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
class Account {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String accountHolder;
	    private Double balance;

	    public Account() {}

	    public Account(String accountHolder, Double balance) {
	        this.accountHolder = accountHolder;
	        this.balance = balance;
	    }

	    public Long getId() { return id; }
	    public String getAccountHolder() { return accountHolder; }
	    public void setAccountHolder(String accountHolder) { this.accountHolder = accountHolder; }
	    public Double getBalance() { return balance; }
	    public void setBalance(Double balance) { this.balance = balance; }
}
