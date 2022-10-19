package com.pags.secunit.service;

import com.pags.secunit.entity.Transaction;
import com.pags.secunit.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction getTransaction(Integer id) {
        return transactionRepository.getReferenceById(id);
    }

    public List<Transaction> allTransactionsOf(Integer id){
        return transactionRepository.allTransactionsOf(id);
    }
}
