package com.homebroker.trade.domain.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OrderBook {


    private List<Order> buyOrders;
    private List<Order> sellOrders;
    public OrderBook(List<Order> buyOrders, List<Order> sellOrders)
    {
        this.buyOrders = buyOrders;
        this.sellOrders = sellOrders;

    }

    public Transaction executeTrade() {
        if (buyOrders.isEmpty() || sellOrders.isEmpty())
            return null; // Não há ordens suficientes para executar uma transação

        Order bestBuyOrder = buyOrders.get(0);
        Order bestSellOrder = sellOrders.get(0);

        if (bestBuyOrder.getPrice() >= bestSellOrder.getPrice()) {
            // Executar a transação
            int quantity = Math.min(bestBuyOrder.getQuantity(), bestSellOrder.getQuantity());
            double price = bestSellOrder.getPrice();

            Transaction transaction = new Transaction(bestBuyOrder.getOrderId(), bestSellOrder.getOrderId(), bestBuyOrder.getWalletId(), bestSellOrder.getWalletId(), quantity, price);
//            transactionHistory.add(transaction);

            System.out.println("Transação executada: Quantidade=" + quantity + ", Preço=" + price);

            bestBuyOrder.setQuantity(bestBuyOrder.getQuantity() - quantity);
            bestSellOrder.setQuantity(bestSellOrder.getQuantity() - quantity);

            if (bestBuyOrder.getQuantity() == 0)
                buyOrders.remove(0);
            if (bestSellOrder.getQuantity() == 0)
                sellOrders.remove(0);

            return transaction;
        }

        return null;
    }


//    public void generateRandomOrders(int numOrders) {
//        Random random = new Random();
//        for (int i = 0; i < numOrders; i++) {
//            int quantity = random.nextInt(10) + 1; // Quantidade aleatória entre 1 e 10
//            int price = random.nextInt(10, 15); // Preço aleatório entre 0 e 100
//            String orderType = random.nextBoolean() ? "buy" : "sell"; // Tipo de ordem aleatório (buy ou sell)
//
//            Order order = new Order(quantity, price, orderType);
//
//            if (orderType.equals("buy"))
//                addBuyOrder(order);
//            else
//                addSellOrder(order);
//
//            System.out.println("Ordem gerada: Quantidade=" + quantity + ", Preço=" + price + ", Tipo=" + orderType);
//
//            this.executeTrade();
//        }
//    }
}