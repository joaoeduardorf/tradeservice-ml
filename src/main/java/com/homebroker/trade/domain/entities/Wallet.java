package com.homebroker.trade.domain.entities;

import java.util.List;

public class Wallet {
    private int walletId;
    private int quantity;
    private double balance;
    private List<Transaction> transactions;

    public Wallet(int walletId, int quantity, double balance) {
        this.walletId = walletId;
        this.quantity = quantity;
        this.balance = balance;
    }

    public void BuyOperation(int quantity, double value){
        quantity +=quantity;
        balance -= value;
    }

    public void SellOperation(int quantity, double value){
        quantity -= quantity;
        balance += value;
    }

    public int getWalletId() {
        return walletId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getBalance() {
        return balance;
    }
}
