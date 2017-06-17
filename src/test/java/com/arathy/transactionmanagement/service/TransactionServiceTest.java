package com.arathy.transactionmanagement.service;

import com.arathy.transactionmanagement.dao.TransactionRepository;
import com.arathy.transactionmanagement.models.Transaction;
import com.arathy.transactionmanagement.models.TransactionStatistics;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class TransactionServiceTest {

    @Mock
    private TransactionRepository mockTransactionRepository;

    @InjectMocks
    private TransactionService transactionServiceMock;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void transactionRepositoryIsCalledForSave() {
        Transaction expectedTransaction = new Transaction(1, 12.0, 123456);
        when(mockTransactionRepository.save(any(Transaction.class))).thenReturn(expectedTransaction);
        Transaction transaction = new Transaction(1, 12.0, 123456);
        Transaction transactionResult = transactionServiceMock.saveTransaction(transaction);

        verify(mockTransactionRepository).save(transaction);
        assertEquals(expectedTransaction, transactionResult);
    }

    @Test
    public void transactionRepositoryIsCalledForGetTransactionStatisticsBeforeLast60Seconds() {
        TransactionStatistics expectedTransactionStatistics = new TransactionStatistics(1.0, 1.0, 1.0, 1.0, 1L);

        when(mockTransactionRepository.getTransactionStaticsAfter(anyLong())).thenReturn(expectedTransactionStatistics);

        TransactionStatistics transactionResult = transactionServiceMock.getTransactionStatisticsBeforeLast60Seconds();

        verify(mockTransactionRepository).getTransactionStaticsAfter(anyLong());
        assertEquals(expectedTransactionStatistics, transactionResult);
    }
}