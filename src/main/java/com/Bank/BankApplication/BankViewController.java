package com.Bank.BankApplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/bank")
public class BankViewController {

	
	private final BankService service;

    public BankViewController(BankService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("accounts", service.getAllAccounts());
        return "home";
    }

    @PostMapping("/create")
    public String createAccount(@RequestParam String holder, @RequestParam double balance, Model model) {
        service.createAccount(holder, balance);
        model.addAttribute("message", "Account created successfully!");
        model.addAttribute("accounts", service.getAllAccounts());
        return "home";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam Long id, @RequestParam double amount, Model model) {
        service.deposit(id, amount);
        model.addAttribute("message", "Deposit successful!");
        model.addAttribute("accounts", service.getAllAccounts());
        return "home";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long id, @RequestParam double amount, Model model) {
        try {
            service.withdraw(id, amount);
            model.addAttribute("message", "Withdrawal successful!");
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        model.addAttribute("accounts", service.getAllAccounts());
        return "home";
    }

    @PostMapping("/balance")
    public String checkBalance(@RequestParam Long id, Model model) {
        try {
            double balance = service.checkBalance(id);
            model.addAttribute("message", "Account Balance: " + balance);
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        model.addAttribute("accounts", service.getAllAccounts());
        return "home";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long fromId, @RequestParam Long toId, @RequestParam double amount, Model model) {
        try {
            service.transfer(fromId, toId, amount);
            model.addAttribute("message", "Transfer successful!");
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        model.addAttribute("accounts", service.getAllAccounts());
        return "home";
    }
}


