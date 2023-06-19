package com.homebroker.trade.Infra.Data;

import com.homebroker.trade.domain.entities.Wallet;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class WalletDB {

    Hashtable<Integer, Wallet> walletDictionary;
    private static WalletDB instance;

    private WalletDB(){
        walletDictionary = new Hashtable<Integer, Wallet>();

    }
    public static WalletDB getInstance() {
        if(instance == null) {
            synchronized(WalletDB.class) {
                if(instance == null) {
                    instance = new WalletDB();
                }
            }
        }
        return instance;
    }

    public void Add(Wallet wallet){
        walletDictionary.put(wallet.getWalletId(), wallet);
    }

    public Wallet Get(int walletId){
        Wallet wallet =  walletDictionary.get(walletId);
        if(wallet == null){
            wallet = new Wallet(walletId, 100,1000);
            walletDictionary.put(walletId, wallet);
        }

        return wallet;
    }

    public Wallet Update(Wallet wallet)
    {
        return walletDictionary.replace(wallet.getWalletId(), wallet);
    }

    public Hashtable<Integer,Wallet> Get(){
        return walletDictionary;
    }

}
