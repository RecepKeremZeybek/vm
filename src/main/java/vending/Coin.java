package vending;

/**
 * Creates Coins
 */
public enum Coin {
    
    PENNY("penny", 1), NICKLE("nickle", 5), DIME("dime", 10), QUARTER("quarter", 25);
    private final String name;
    private final int value;
    
    Coin(String name, int value) {
        this.name = name;
        this.value = value;
    }
    
    public int getValue() {
        
        return value;
    }
    
}
