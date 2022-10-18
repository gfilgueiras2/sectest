package com.pags.secunit.controller;

import com.pags.secunit.entity.Transaction;
import com.pags.secunit.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("banks")
@RestController
@RequiredArgsConstructor
public class BankController {

    private final TransactionService transactionService;

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/transactions/{id}")
    public Transaction getTransaction(@PathVariable("id") Integer id) {
        return transactionService.getTransaction(id);
    }

    @GetMapping("/transactions/all/{userId}")
    public List<Transaction> allTransactionsOf(@PathVariable("userId") Integer id) {
        return transactionService.allTransactionsOf(id);
    }

}
