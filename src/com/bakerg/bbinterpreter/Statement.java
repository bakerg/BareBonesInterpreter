package com.bakerg.bbinterpreter;

import java.util.ArrayList;

/**
 * Represents a Bare Bones statement with an operand and any number of
 * arguments.
 *
 * @author Geoff
 */
public class Statement {

    private final String operation;
    private final ArrayList<String> args;

    /**
     *
     * @param tokens The tokens in the statement with the operand in index 0
     */
    public Statement(ArrayList<String> tokens) {
        this.operation = tokens.get(0);
        tokens.remove(0);
        args = tokens;
    }

    /**
     * Get this Statement's arguments
     *
     * @return ArrayList containing this statement's arguments
     */
    public ArrayList<String> getArgs() {
        return this.args;
    }

    /**
     * Get this Statement's operation name
     *
     * @return Name of this Statement's operation
     */
    public String getOperation() {
        return this.operation;
    }

    @Override
    public String toString() {
        String out = "Operation: " + operation + " Args: ";
        out = args.stream().map((s) -> s + ", ").reduce(out, String::concat);
        return out;
    }
}
