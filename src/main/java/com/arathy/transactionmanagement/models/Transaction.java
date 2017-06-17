package com.arathy.transactionmanagement.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;

    @Column
    double amount;

    @Column
    long timestamp;

    public long getTimestamp() {
        return timestamp;
    }
}
