package com.bakerg.bbinterpreter;

/**
 * Exception thrown when an unknown operation is encountered in a program
 *
 * @author Geoff
 */
public class NoSuchOperationException extends Exception {

    private String message = "Invalid Bare Bones operation in code!";

    public NoSuchOperationException() {
        super();
    }

    public NoSuchOperationException(String msg) {
        super(msg);
        this.message = msg;
    }

    @Override
    public String toString() {
        return message;
    }
}
