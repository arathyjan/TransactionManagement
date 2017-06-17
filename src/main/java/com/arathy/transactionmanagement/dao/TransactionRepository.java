package com.arathy.transactionmanagement.dao;

import com.arathy.transactionmanagement.models.Transaction;
import com.arathy.transactionmanagement.models.TransactionStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT new com.arathy.transactionmanagement.models.TransactionStatistics(SUM(t.amount) as sum, avg(t.amount) as avg, max(t.amount) as max, min(t.amount) as min, count(*) as count) FROM Transaction t WHERE t.timestamp >= :timestamp")
    TransactionStatistics getTransactionStaticsAfter(@Param("timestamp") long timestamp);
}