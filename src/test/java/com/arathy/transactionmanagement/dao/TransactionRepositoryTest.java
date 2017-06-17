package com.arathy.transactionmanagement.dao;

import com.arathy.transactionmanagement.models.Transaction;
import com.arathy.transactionmanagement.models.TransactionStatistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionRepositoryTest {
    @Autowired
    TransactionRepository transactionRepository;
    @Test
    public void testGetAvgSumMinMaxAndCountForRecordsAfterTransactions() {
        long before60s = ZonedDateTime.now().minusSeconds(70).toInstant().toEpochMilli();
        long within60s = ZonedDateTime.now().minusSeconds(30).toInstant().toEpochMilli();
        Instant nowInstant = ZonedDateTime.now().toInstant();
        long within0s = nowInstant.toEpochMilli();

        Transaction transactionBeforeTimeStamp = new Transaction(1, 20.0, before60s);
        Transaction transactionAfterTimeStamp1 = new Transaction(2, 40.0, within60s);
        Transaction transactionAfterTimeStamp2 = new Transaction(3, 60.0, within0s);
        transactionRepository.save(transactionBeforeTimeStamp);
        transactionRepository.save(transactionAfterTimeStamp1);
        transactionRepository.save(transactionAfterTimeStamp2);

        TransactionStatistics transactionStatistics = transactionRepository.getTransactionStaticsAfter(nowInstant.minusSeconds(60).toEpochMilli());
        assertEquals(50.0, transactionStatistics.getAvg(), 0);
        assertEquals(40.0, transactionStatistics.getMin(), 0);
        assertEquals(60.0, transactionStatistics.getMax(), 0);
        assertEquals(100.0, transactionStatistics.getSum(), 0);
        assertEquals(2, transactionStatistics.getCount(), 0);
    }
}