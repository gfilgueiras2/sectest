package com.pags.secunit.controller;

import com.pags.secunit.entity.Transaction;
import com.pags.secunit.service.TransactionService;
import dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("banks")
@RestController
@RequiredArgsConstructor
public class BankController {

    private final TransactionService transactionService;

    @PostMapping("/transactions")
    public BaseResponse<Transaction> createTransaction(@RequestBody Transaction transaction,
                                                       @RequestHeader("password") String password) throws Exception {
        return transactionService.createTransaction(transaction, password);
    }

    @GetMapping("/transactions/{id}")
    public Transaction getTransaction(@PathVariable("id") Integer id) {
        return transactionService.getTransaction(id);
    }

    @GetMapping("/transactions/all/{userId}")
    public BaseResponse<List<Transaction>> allTransactionsOf(@PathVariable("userId") Integer id, @RequestHeader("password") String password) throws Exception {
        return transactionService.allTransactionsOf(id, password);
    }

}
