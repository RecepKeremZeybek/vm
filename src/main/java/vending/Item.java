package vending;

/**
 * Creates Item with values
 */
public enum Item {
    
    COKE("coke",25),PEPSI("pepsi",35),SODA("soda",45);
    private final String name;
    private final int price;
    
    Item(String name, int price){
    
        this.name=name;
        this.price=price;
    }
    
    public int getPrice() {
        
        return price;
    }
    
}

