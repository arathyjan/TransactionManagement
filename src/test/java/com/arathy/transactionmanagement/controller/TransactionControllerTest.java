package com.arathy.transactionmanagement.controller;

import com.arathy.transactionmanagement.models.Transaction;
import com.arathy.transactionmanagement.models.TransactionStatistics;
import com.arathy.transactionmanagement.service.TransactionService;
import com.arathy.transactionmanagement.utils.DateTimeUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testSaveTransactionCallsServiceAndReturn201IfTimeStampLessThan60s() {
        TransactionController transactionController = new TransactionController(transactionService);
        long milli = ZonedDateTime.now(UTC).toInstant().toEpochMilli();
        Transaction transaction = new Transaction(1, 12.5, milli);
        Transaction expectedTransaction = new Transaction();
        when(transactionService.saveTransaction(transaction)).thenReturn(expectedTransaction);

        ResponseEntity<Void> saveTransaction = transactionController.saveTransaction(transaction);

        verify(transactionService).saveTransaction(transaction);
        assertEquals(201, saveTransaction.getStatusCode().value());
    }

    @Test
    public void testSaveTransactionCallsServiceAndReturn204IfTimeStampMoreThan60s() {
        TransactionController transactionController = new TransactionController(transactionService);
        long milli = ZonedDateTime.now(UTC).minusSeconds(60).toInstant().toEpochMilli();
        Transaction transaction = new Transaction(1, 12.5, milli);
        Transaction expectedTransaction = new Transaction();
        when(transactionService.saveTransaction(transaction)).thenReturn(expectedTransaction);

        ResponseEntity<Void> saveTransaction = transactionController.saveTransaction(transaction);

        verify(transactionService).saveTransaction(transaction);
        assertEquals(204, saveTransaction.getStatusCode().value());
    }

    @Test
    public void testIfGetTransactionStatisticsCallsService() {
        TransactionController transactionController = new TransactionController(transactionService);
        TransactionStatistics expectedTransactionStatistics = new TransactionStatistics(1.0, 1.0, 1.0, 1.0, 1L);
        when(transactionService.getTransactionStatisticsBeforeLast60Seconds()).thenReturn(expectedTransactionStatistics);

        TransactionStatistics transactionStatistics = transactionController.getStatistics();

        assertEquals(expectedTransactionStatistics, transactionStatistics);
        verify(transactionService).getTransactionStatisticsBeforeLast60Seconds();
    }

}