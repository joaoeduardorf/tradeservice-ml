package com.homebroker.trade.domain.entities;


import com.homebroker.trade.domain.objectvalue.OrderStatus;
import com.homebroker.trade.domain.objectvalue.OrderType;

import java.util.UUID;

public class Order {
    private UUID orderId;
    private int walletId;
    private int quantity;
    private int price;
    private OrderType orderType;
    private OrderStatus orderStatus;
    private long timestampRequested;
    private long timestampQueued;
    private long timestampOrdered;
    private long timestampExecuted;
    //    private List<long> timestampPartExecuted;
//    private long timestampExpired;
    private long timestampCanceled;
    private long timestampRejected;

    public Order(UUID orderId, int walletId, int quantity, int price, OrderType orderType, OrderStatus orderStatus, long timestampRequested) {
        this.orderId = orderId;
        this.walletId = walletId;
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.timestampRequested = timestampRequested;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public long getTimestampRequested() {
        return timestampRequested;
    }

    public int getWalletId() {
        return walletId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setTimestampQueued(long timestampQueued) {
        this.timestampQueued = timestampQueued;
    }

    public long getTimestampOrdered() {
        return timestampOrdered;
    }

    public void setTimestampOrdered(long timestampOrdered) {
        this.timestampOrdered = timestampOrdered;
    }

    public long getTimestampExecuted() {
        return timestampExecuted;
    }

    public void setTimestampExecuted(long timestampExecuted) {
        this.timestampExecuted = timestampExecuted;
    }

    public long getTimestampCanceled() {
        return timestampCanceled;
    }

    public void setTimestampCanceled(long timestampCanceled) {
        this.timestampCanceled = timestampCanceled;
    }

    public long getTimestampRejected() {
        return timestampRejected;
    }

    public void setTimestampRejected(long timestampRejected) {
        this.timestampRejected = timestampRejected;
    }
}