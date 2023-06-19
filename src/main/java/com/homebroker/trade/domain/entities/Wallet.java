package com.homebroker.trade.domain.entities;

import java.util.List;

public class WalletId {
    private int walletId;
    private int quantity;
    private int balance;

    private List<Transaction> transactions;

    public WalletId(int walletId) {
        this.walletId = walletId;
    }

    void BuyOperation(int quantity, int value){
        quantity +=quantity;
        balance -= value;
    }

    void SellOperation(int quantity, int value){
        quantity -= quantity;
        balance += value;
    }
}
