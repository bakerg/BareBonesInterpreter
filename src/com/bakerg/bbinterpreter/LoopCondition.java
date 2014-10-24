package com.bakerg.bbinterpreter;

/**
 *
 * @author Geoff
 */
public class LoopCondition {

    private final BBVariable v;
    private final int target, branchTo;
    private boolean verbose;

    /**
     * Create a new LoopCondition
     *
     * @param v Looping variable
     * @param target Variable's target value
     * @param branchTo Where to branch to when the loop condition is met
     */
    public LoopCondition(BBVariable v, int target, int branchTo) {
        this.v = v;
        this.target = target;
        this.branchTo = branchTo;
    }

    /**
     * Create a new verbose LoopCondition
     *
     * @param v Looping variable
     * @param target Variable's target value
     * @param branchTo Where to branch to when the loop condition is met
     * @param verbose Whether this LoopCondition is verbose
     */
    public LoopCondition(BBVariable v, int target, int branchTo, boolean verbose) {
        this.v = v;
        this.target = target;
        this.branchTo = branchTo;
        this.verbose = verbose;
    }

    /**
     *
     * @return True when the loop condition is met
     */
    public boolean loopFinished() {
        if (verbose) {
            System.out.println(this);
        }
        return v.getValue() == this.target;
    }

    /**
     * Get where to branch to after this loop
     *
     * @return Address to branch to
     */
    public int getBranchTo() {
        return this.branchTo;
    }

    @Override
    public String toString() {
        return "LoopCondition: " + v.getName() + " not " + target + " Branch to: " + branchTo;
    }
}
