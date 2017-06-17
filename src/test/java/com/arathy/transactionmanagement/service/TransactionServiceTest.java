package com.arathy.transactionmanagement.service;

import com.arathy.transactionmanagement.DAO.TransactionRepository;
import com.arathy.transactionmanagement.models.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class TransactionServiceTest {

    @Mock
    private TransactionRepository mockTransactionRepository;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void transactionRepositoryIsCalledForSave() {
        TransactionService transactionService = new TransactionService(mockTransactionRepository);
        Transaction expectedTransaction = new Transaction();
        when(mockTransactionRepository.save(any(Transaction.class))).thenReturn(expectedTransaction);
        Transaction transaction = new Transaction();
        Transaction transactionResult = transactionService.saveTransaction(transaction);

        verify(mockTransactionRepository).save(transaction);
        assertEquals(expectedTransaction, transactionResult);

    }
}