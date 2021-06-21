package vending;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static vending.Coin.*;
import static vending.Item.*;

/**
 * Test Class for Vending Machine
 */
public class VendingTest {
    
    
    VendingMachineImp vendingMachineImp = new VendingMachineImp();
    
    
    /**
     * Common scenario allows all types of coins accepting
     */
    @Test
    public void test1() {
        
        vendingMachineImp.startMachine();
        vendingMachineImp.feedCoin(PENNY);//1
        vendingMachineImp.feedCoin(NICKLE);//5
        vendingMachineImp.feedCoin(DIME);//10
        vendingMachineImp.feedCoin(QUARTER);//25
        vendingMachineImp.feedCoin(QUARTER);//25
        vendingMachineImp.selectItem(SODA);//45
    
        Map<Coin,Integer> expectedReturn = new HashMap<>();
        expectedReturn.put(DIME, 2);
        expectedReturn.put(PENNY, 1);
        
        Assertions.assertEquals(
                expectedReturn, vendingMachineImp.checkOutAndReturnChangesIfAny()
        );
       
        vendingMachineImp.resetOperation();
        
        
    }
    
    /**
     * Insufficient Cash Testing
     */
    @Test
    public void test2() {
        
        assertThrows(vending.insufficientCashException.class, () -> {
            vendingMachineImp.startMachine();
            vendingMachineImp.feedCoin(DIME);//10
            vendingMachineImp.feedCoin(NICKLE);//5
            vendingMachineImp.selectItem(COKE);//25
            vendingMachineImp.selectItem(PEPSI);//35
            vendingMachineImp.selectItem(SODA);//45
            vendingMachineImp.checkOutAndReturnChangesIfAny();
            
           
            
        }, "Insufficient Amount is 90 PENNY");
    }
    
    
    
    /**
     * Insufficient (Out of stock) Product Testing
     */
    @Test
    public void test3() {
        
        assertThrows(vending.InsufficientAmountOfProductException.class, () -> {
            vendingMachineImp.startMachine();
            vendingMachineImp.feedCoin(QUARTER);//25
            vendingMachineImp.feedCoin(QUARTER);//25
            vendingMachineImp.feedCoin(QUARTER);//25
            vendingMachineImp.feedCoin(QUARTER);//25
            vendingMachineImp.feedCoin(QUARTER);//25
            vendingMachineImp.feedCoin(QUARTER);//25
            vendingMachineImp.selectItem(COKE);//25
            vendingMachineImp.selectItem(COKE);//25
            vendingMachineImp.selectItem(COKE);//25
            vendingMachineImp.selectItem(COKE);//25
            vendingMachineImp.selectItem(COKE);//25
            vendingMachineImp.selectItem(COKE);//25
            vendingMachineImp.checkOutAndReturnChangesIfAny();
            vendingMachineImp.dispatchItem();
            
        }, "COKE is not available");
        
    }
    
    /**
     * Reset operation testing
     */
    @Test
    public void test4() {
        
        vendingMachineImp.startMachine();
        vendingMachineImp.feedCoin(NICKLE);//5
        vendingMachineImp.feedCoin(DIME);//10
        vendingMachineImp.feedCoin(QUARTER);//25
        vendingMachineImp.selectItem(SODA);//45
        
        Map<Coin,Integer> expectedReturn = new HashMap<>();
        expectedReturn.put(NICKLE, 1);
        expectedReturn.put(DIME, 1);
        expectedReturn.put(QUARTER, 1);
        
        assertEquals(expectedReturn,vendingMachineImp.resetOperationAndReturnChanges());
        
        
        vendingMachineImp.resetOperation();
        
        assertTrue(vendingMachineImp.resetOperationAndReturnChanges().isEmpty());
        
    }
    
    
}
