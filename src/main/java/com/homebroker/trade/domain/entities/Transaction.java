package com.homebroker.trade.domain.entities;

import java.util.UUID;

public class Transaction {

    private UUID buyerOrderId;
    private UUID sellerOrderId;
    private int buyerWalletId;
    private int sellerWalletId;
    private int quantity;
    private double price;
    private long timestamp;

    public Transaction(UUID buyerOrderId, UUID sellerOrderId,int buyerWalletId, int sellerWalletId, int quantity, double price) {
        this.buyerOrderId = buyerOrderId;
        this.sellerOrderId = sellerOrderId;
        this.buyerWalletId = buyerWalletId;
        this.sellerWalletId = sellerWalletId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getSellerWalletId() {
        return sellerWalletId;
    }

    public int getBuyerWalletId() {
        return buyerWalletId;
    }

    public UUID getBuyerOrderId() {
        return buyerOrderId;
    }

    public UUID getSellerOrderId() {
        return sellerOrderId;
    }
}