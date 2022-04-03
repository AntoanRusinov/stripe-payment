package com.playtomic.tests.wallet.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "wallet")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.valueOf(0);

    @Column(name = "currency")
    private Currency currency = Currency.getInstance("USD");

//    @ElementCollection
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
        transaction.setWallet(this);
    }
}