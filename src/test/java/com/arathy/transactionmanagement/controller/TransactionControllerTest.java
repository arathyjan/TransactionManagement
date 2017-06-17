package com.arathy.transactionmanagement.controller;

import com.arathy.transactionmanagement.models.Transaction;
import com.arathy.transactionmanagement.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
    public void saveTransaction() {
        TransactionController transactionController = new TransactionController(transactionService);
        Transaction transaction = new Transaction();
        Transaction expectedTransaction = new Transaction();
        when(transactionService.saveTransaction(transaction)).thenReturn(expectedTransaction);

        Transaction saveTransaction = transactionController.saveTransaction(transaction);

        assertEquals(expectedTransaction, saveTransaction);
        verify(transactionService).saveTransaction(transaction);
    }

}