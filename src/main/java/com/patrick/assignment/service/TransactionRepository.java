package com.patrick.assignment.service;

import com.patrick.assignment.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.date BETWEEN :startDate AND :endDate")
    List<Transaction> findTransactionsBetweenStartDateEndDate(
            @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT t FROM Transaction t WHERE t.product.name = ?1")
    List<Transaction> findTransactionByProductName(String productName);

}
