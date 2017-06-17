package com.arathy.transactionmanagement.controller;

import com.arathy.transactionmanagement.models.Transaction;
import com.arathy.transactionmanagement.models.TransactionStatistics;
import com.arathy.transactionmanagement.service.TransactionService;
import com.arathy.transactionmanagement.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public TransactionStatistics getStatistics() {
        return transactionService.getTransactionStatisticsBeforeLast60Seconds();
    }

    @RequestMapping(value = "/transactions", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Void> saveTransaction(@RequestBody Transaction transaction) {
        transactionService.saveTransaction(transaction);

        if (transaction.getTimestamp() >= DateTimeUtils.getEpochMillisForUTCForLast60s())
            return ResponseEntity.status(201).build();
        return ResponseEntity.status(204).build();
    }
}