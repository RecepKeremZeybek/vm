package vending;


/**
 * Creates Exception with message
 */
public class InsufficientAmountOfProductException extends RuntimeException {
    private final String message;
    private final Item item;
    public InsufficientAmountOfProductException(Item item, String string) {
        this.item = item;
        this.message = string;
       
    }
    
    @Override
    public String getMessage() {
        return  item + message;
    }
}