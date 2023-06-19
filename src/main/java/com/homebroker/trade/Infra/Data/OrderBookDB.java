package com.homebroker.trade.Infra.Data;

import com.homebroker.trade.domain.entities.Order;
import com.homebroker.trade.domain.entities.OrderBook;
import com.homebroker.trade.domain.entities.Transaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderBookDB {

    private List<Order> buyOrders;
    private List<Order> sellOrders;
    private List<Transaction> transactionHistory;
    private static OrderBookDB instance;
    private OrderBookDB() {
        buyOrders = new ArrayList<>();
        sellOrders = new ArrayList<>();
        transactionHistory = new ArrayList<>();
    }

    public static OrderBookDB getInstance() {
        if(instance == null) {
            synchronized(OrderBookDB.class) {
                if(instance == null) {
                    instance = new OrderBookDB();
                }
            }
        }
        return instance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void printTransactionHistory() {
        System.out.println("Histórico de Transações:");
        for (Transaction transaction : transactionHistory) {
            System.out.println("Quantidade=" + transaction.getQuantity() + ", Preço=" + transaction.getPrice());
        }
    }
    public void addBuyOrder(Order order) {
        order.setTimestampQueued(Instant.now().toEpochMilli());
        buyOrders.add(order);
        Collections.sort(buyOrders, (o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice())); // Classificar ordens de compra por preço decrescente
    }

    public void addSellOrder(Order order) {
        order.setTimestampQueued(Instant.now().toEpochMilli());
        sellOrders.add(order);
        Collections.sort(sellOrders, (o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice())); // Classificar ordens de venda por preço crescente
    }

    public List<Order> GetBuyerOrder(){
        return buyOrders;
    }

    public List<Order> GetSellerOrder(){
        return sellOrders;
    }

    public void AddTransaction(Transaction transaction){
        transactionHistory.add(transaction);
    }

    public List<Transaction> GetTransactionHistory(){
        return transactionHistory;
    }
}
