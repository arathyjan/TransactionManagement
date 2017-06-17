package com.arathy.transactionmanagement.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;

    @Column
    Double amount;

    @Column
    Double timestamp;
}
