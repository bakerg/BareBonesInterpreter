package com.bakerg.bbinterpreter;

/**
 * Represents a Bare Bones variable
 *
 * @author Geoff
 */
public class BBVariable {

    private final String name;
    private int value = 0;
    private boolean verbose = true;

    /**
     * Create a new verbose variable initialised to 0
     *
     * @param name The name of this variable
     */
    public BBVariable(String name) {
        this.name = name;
    }

    /**
     * Create a new verbose variable initialised to value
     *
     * @param name The name of this variable
     * @param value Initial value
     */
    public BBVariable(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Create a new variable initialised to value with specified verbosity
     *
     * @param name Name of this variable
     * @param value Initial value
     * @param verbose Whether this variable is verbose
     */
    public BBVariable(String name, int value, boolean verbose) {
        this.name = name;
        this.value = value;
        this.verbose = verbose;
    }

    /**
     * Get this variable's value
     *
     * @return The current value of this variable
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Get this variable's name
     *
     * @return The variable's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Increment this variable
     */
    public void increment() {
        value++;
        if (verbose) {
            System.out.println(this);
        }
    }

    /**
     * Get whether this variable is verbose
     *
     * @return True if this variable is verbose
     */
    public boolean getVerbose() {
        return this.verbose;
    }

    /**
     * Set whether this variable is verbose
     *
     * @param b True to make this variable verbose
     */
    public void setVerbose(boolean b) {
        this.verbose = b;
    }

    /**
     * Decrement this variable
     *
     * @return Whether decrementing was successful (will fail if result is
     * negative)
     */
    public boolean decrement() {
        boolean canDecrement = value > 0;
        this.value = canDecrement ? value -= 1 : value;
        if (verbose) {
            System.out.println(this);
        }
        return canDecrement;
    }

    /**
     * Clear this variable, setting it to 0
     */
    public void clear() {
        this.value = 0;
        if (verbose) {
            System.out.println(this);
        }
    }

    @Override
    public String toString() {
        return "Variable: " + this.name + " Value: " + this.value;
    }
}
