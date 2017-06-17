package com.arathy.transactionmanagement.service;

import com.arathy.transactionmanagement.dao.TransactionRepository;
import com.arathy.transactionmanagement.models.Transaction;
import com.arathy.transactionmanagement.models.TransactionStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

@Service
public class TransactionService {
    TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public TransactionStatistics getTransactionStatisticsBeforeLast60Seconds() {
        return transactionRepository.getTransactionStaticsAfter(getEpochMillisForUTCForLast60s());
    }

    private long getEpochMillisForUTCForLast60s() {
        return ZonedDateTime
                .now(UTC)
                .minusSeconds(60)
                .toInstant()
                .toEpochMilli();
    }
}
