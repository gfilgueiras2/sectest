package com.pags.secunit.repository;

import com.pags.secunit.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query(value = "SELECT * FROM TRANSACTION WHERE SENT_ID = :id", nativeQuery = true)
    List<Transaction> allTransactionsOf(Integer id);
}
