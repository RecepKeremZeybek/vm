package vending;

/**
 * Creates Exception with message
 */
public class insufficientCashException extends RuntimeException {
    private final String message;
    private final int remaining;
    
    public insufficientCashException(String message, int remaining) {
        this.message = message;
        this.remaining = remaining;
    }
    
    public int getRemaining() {
        return remaining;
    }
    
    @Override
    public String getMessage() {
        return message + " is " + Math.abs(remaining) +" PENNY";
    }
}
