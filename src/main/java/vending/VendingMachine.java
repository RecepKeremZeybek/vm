package vending;

import java.util.Map;

public interface VendingMachine {
    
     void startMachine();
    
     void feedCoin(Coin coin);
     
     void selectItem(Item item);
     
     Map<Coin,Integer> checkOutAndReturnChangesIfAny();
     
     void dispatchItem();
     
     Map<Coin,Integer> resetOperationAndReturnChanges();
     
     void resetOperation();
  
}
