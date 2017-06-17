package com.arathy.transactionmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionStatistics {
    Double sum;
    Double avg;
    Double max;
    Double min;
    Long count;
}