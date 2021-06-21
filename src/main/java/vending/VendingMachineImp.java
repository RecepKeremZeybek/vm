package vending;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete class implementing main activities
 */
public class VendingMachineImp implements VendingMachine {
    
    final static Map<Coin, Integer> cash = new HashMap<Coin, Integer>();
    final static Map<Item, Integer> stock = new HashMap<Item, Integer>();
    
    
    @Override
    public void startMachine() {
        resetOperation();
        
        for (Coin c : Coin.values()) {
            cash.put(c, 5);
        }
        
        for (Item i : Item.values()) {
            stock.put(i, 5);
        }
        
    }
    
    @Override
    public void feedCoin(Coin coin) {
        Bucket.balance(coin);
    }
    
    @Override
    public void selectItem(Item item) {
       
        Bucket.balance(item);
        
    }
    
    
    @Override
    public Map<Coin,Integer> checkOutAndReturnChangesIfAny() {
    
       return Bucket.dispenseChange();
    }
    
    @Override
    public void dispatchItem() {

    }

    @Override
    public Map<Coin,Integer> resetOperationAndReturnChanges() {
       return Bucket.returnChange();
    }
    
    @Override
    public void resetOperation() {
        Bucket.reset();
    }
}
