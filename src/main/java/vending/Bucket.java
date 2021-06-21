package vending;

import java.util.HashMap;
import java.util.Map;


/**
 * Handles temporary excecutions
 */
public class Bucket {
    
    private static final Map<Coin, Integer> coinCO = new HashMap<>();
    private static final Map<Coin, Integer> changeCO = new HashMap<>();
    private static final Map<Item, Integer> itemCO = new HashMap<>();
    private static int coinSum;
    private static int balance;
    private static int itemSum;
    
    
    /**
     * Adds coin to the temp bucket
     * @param coin
     * @return
     */
    public static Map<Coin, Integer> balance(Coin coin) {
        
        
        if (coinCO.containsKey(coin)) {
            coinCO.put(coin, coinCO.get(coin) + 1);
        } else {
            coinCO.put(coin, 1);
        }
        return coinCO;
    }
    
    /**
     * Adds items to temp bucket
     * @param item
     * @return
     */
    public static Map<Item, Integer> balance(Item item) {
        
        if (itemCO.containsKey(item)) {
            itemCO.put(item, itemCO.get(item) + 1);
        } else {
            itemCO.put(item, 1);
        }
        
        // Checks if requested quantity is stocked
        if (VendingMachineImp.stock.get(item) < itemCO.get(item)) {
            insufficientProduct(item);
        }
        return itemCO;
    }
    
    /**
     * Calculates balance from Coin and Items entered
     * @return
     */
    public static int checkout() {
        for (Coin c : coinCO.keySet()) {
            coinSum += coinCO.get(c) * c.getValue();
        }
        for (Item i : itemCO.keySet()) {
            itemSum += itemCO.get(i) * i.getPrice();
        }
        return balance = coinSum - itemSum;
    }
    
    /**
     * Checks cash whether it is sufficient
     * @return
     */
    public static Map<Coin,Integer> dispenseChange() {
        balance = checkout();
        if (balance < 0) {
            insufficientCash(balance);
        }
        
        return dispenseChange(balance);
        
    }
    
    
    /**
     * Dispense return nominated changes starting from the most valuable
     * @param balance
     * @return
     */
    public static Map<Coin, Integer> dispenseChange(int balance) {
        
        
        if (balance >= Coin.QUARTER.getValue()) {
            int Q = balance / Coin.QUARTER.getValue();
            changeCO.put(Coin.QUARTER, Q);
            balance %= Coin.QUARTER.getValue();
        } else if (balance >= Coin.DIME.getValue()) {
            int D = balance / Coin.DIME.getValue();
            changeCO.put(Coin.DIME, D);
            balance %= Coin.DIME.getValue();
        } else if (balance >= Coin.NICKLE.getValue()) {
            int N = balance / Coin.NICKLE.getValue();
            changeCO.put(Coin.NICKLE, N);
            balance %= Coin.NICKLE.getValue();
        }
            changeCO.put(Coin.PENNY, balance);
        
        
        return changeCO;
    }
    
    
    /**
     * Throws  insufficientCashException Exception
     * @param balance
     */
    private static void insufficientCash(int balance) {
        
        throw new insufficientCashException("Insufficient Amount", balance);
        
    }
    
    
    /**
     * @param item Throws InsufficientAmountOfProductException Exception
     */
    private static void insufficientProduct(Item item) {
        
        throw new InsufficientAmountOfProductException(item, " is not available");
        
    }
    
    
    /**
     * Reset the memories
     */
    public static void reset() {
        
        coinCO.clear();
        itemCO.clear();
        changeCO.clear();
        VendingMachineImp.cash.clear();
        VendingMachineImp.stock.clear();
        
        coinSum = 0;
        balance = 0;
        itemSum = 0;
        
        
    }
    
    
    /**
     * Returns Change Map
     * @return
     */
    public static Map<Coin,Integer> returnChange() {
        
        return coinCO;
    }
    
    
}
