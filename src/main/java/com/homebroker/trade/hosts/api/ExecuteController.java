package com.homebroker.trade.hosts.api;

import com.homebroker.trade.Infra.Data.OrderBookDB;
import com.homebroker.trade.Infra.Data.WalletDB;
import com.homebroker.trade.domain.entities.Order;
import com.homebroker.trade.domain.entities.OrderBook;
import com.homebroker.trade.domain.entities.Transaction;
import com.homebroker.trade.domain.entities.Wallet;
import com.homebroker.trade.domain.objectvalue.OrderStatus;
import com.homebroker.trade.domain.objectvalue.OrderType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

@RestController
//@Component
//@Service
public class ExecuteController {

    private OrderBookDB orderBookDB;
    private WalletDB walletDB;
    public ExecuteController(){
        walletDB = WalletDB.getInstance();
        orderBookDB = OrderBookDB.getInstance();
    }
    @GetMapping("/execute")
//    @Async
    String Execute() {

        try {
            // Define o endereço da API
            String apiUrl = "http://localhost:8080/orders";

            // Cria a URL de requisição
            URL url = new URL(apiUrl);

            // Abre a conexão HTTP
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Verifica se a resposta foi bem-sucedida
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Lê a resposta da API
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                // Converte a resposta em um objeto JSON e extrai os atributos
                JSONArray orders = new JSONArray(response.toString());

                // Itera sobre cada pedido na lista
                for (int i = 0; i < orders.length(); i++) {
                    JSONObject order = orders.getJSONObject(i);

                    // Extrai os atributos do pedido
                    UUID orderId = UUID.fromString(order.getString("orderId"));
                    int walletId = order.getInt("walletId");
                    int quantity = order.getInt("quantity");
                    int price = order.getInt("price");
                    OrderType orderType = OrderType.valueOf(order.getString("orderType"));
                    OrderStatus orderStatus = OrderStatus.valueOf(order.getString("orderStatus"));
                    long timestamp = order.getLong("timestampRequested");

                    Order order1 = new Order(orderId, walletId,quantity,price,orderType,orderStatus,timestamp);
                    if(OrderType.BUY == order1.getOrderType()){
                        orderBookDB.addBuyOrder(order1);
                    }else{
                        orderBookDB.addSellOrder(order1);
                    }

                    OrderBook orderBook = new OrderBook(orderBookDB.GetBuyerOrder(), orderBookDB.GetSellerOrder());
                    Transaction transaction = orderBook.executeTrade();
                    if(transaction != null){
                        Wallet buyerWallet = walletDB.Get(transaction.getBuyerWalletId());
                        Wallet sellerWallet = walletDB.Get(transaction.getSellerWalletId());
                        buyerWallet.BuyOperation(transaction.getQuantity(), transaction.getPrice());
                        sellerWallet.SellOperation(transaction.getQuantity(), transaction.getPrice());
                        walletDB.Update(buyerWallet);
                        walletDB.Update(sellerWallet);
                        orderBookDB.AddTransaction(transaction);
                    }
                }
            } else {
                System.out.println("Falha na requisição. Código de resposta: " + conn.getResponseCode());
            }

            // Fecha a conexão HTTP
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/transactionhistory")
    List<Transaction> TransactionHistory() {
        return orderBookDB.getTransactionHistory();
    }

    @GetMapping("/wallets")
    Hashtable<Integer,Wallet> GetWallets() {
        return walletDB.Get();
    }
}
