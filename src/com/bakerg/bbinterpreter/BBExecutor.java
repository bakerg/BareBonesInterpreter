package com.bakerg.bbinterpreter;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Executes Bare Bones statements
 *
 * @author Geoff
 */
public class BBExecutor {

    private ArrayList<BBVariable> variables = new ArrayList();
    private ArrayDeque<LoopCondition> loops = new ArrayDeque();
    private final BBParser bbp;
    private boolean verbose;

    /**
     * Create a new Bare Bones Executor with the specified program
     *
     * @param bbcode Bare Bones program
     */
    public BBExecutor(String bbcode) {
        this.bbp = new BBParser(bbcode);
    }

    /**
     * Create a new Bare Bones Executor with the specified program
     *
     * @param bbcode Bare Bones program
     * @param verbose Output extra information
     */
    public BBExecutor(String bbcode, boolean verbose) {
        this.bbp = new BBParser(bbcode);
        this.verbose = verbose;
    }

    /**
     * Executes the entire program
     *
     * @return True if all execute calls returned true
     * @throws NoSuchOperationException
     */
    public boolean executeProgram() throws NoSuchOperationException {
        boolean successful = true;
        while (bbp.hasNext()) {
            successful = successful ? this.execute(bbp.nextStatement()) : false; //Successful only if all execute calls are successful
        }
        return successful;
    }

    /**
     * Executes a single statement
     *
     * @param st Statement to be executed
     * @return Boolean indicating whether the operation was successful
     * @throws NoSuchOperationException
     */
    private boolean execute(Statement st) throws NoSuchOperationException {
        boolean successful = false;
        if (verbose) {
            System.out.println(st);
        }
        switch (st.getOperation()) {
            case "incr":
                successful = incr(st.getArgs());
                break;
            case "decr":
                successful = decr(st.getArgs());
                break;
            case "clear":
                successful = clear(st.getArgs());
                break;
            case "while":
                successful = whileLoop(st.getArgs());
                break;
            case "end":
                successful = end();
                break;
            default:
                throw new NoSuchOperationException();
        }
        return successful;
    }

    /**
     * Perform the incr operation on the argument
     *
     * @param args Arguments of the statement
     * @return True if the variable was successfully incremented
     */
    private boolean incr(ArrayList<String> args) {
        getVariable(args.get(0)).increment();
        return true;
    }

    /**
     * Perform the decr operation on the argument
     *
     * @param args Arguments of the statement
     * @return True if the variable was successfully decremented
     */
    private boolean decr(ArrayList<String> args) {
        return getVariable(args.get(0)).decrement();
    }

    /**
     * Perform the clear operation on the argument
     *
     * @param args Arguments of the statement
     * @return True if the variable was successfully cleared
     */
    private boolean clear(ArrayList<String> args) {
        getVariable(args.get(0)).clear();
        return true;
    }

    /**
     * Create a new LoopCondition
     *
     * @param args Arguments to the while loop
     * @return True if the loop was successful
     */
    private boolean whileLoop(ArrayList<String> args) {
        BBVariable loopVariable = getVariable(args.get(0));
        int target = Integer.parseInt(args.get(2));
        LoopCondition lc = new LoopCondition(loopVariable, target, bbp.getCurrentAddress() - 1, verbose);
        if (lc.loopFinished()) {
            bbp.branch(findWhileEnd(bbp.getCurrentAddress()) + 1);
        } else {
            loops.push(lc);
        }
        return true;
    }

    /**
     * Check whether a loop has ended
     *
     * @return True if the check was successful
     */
    private boolean end() {
        if (!loops.isEmpty()) {
            LoopCondition lc = loops.pop();
            bbp.branch(lc.getBranchTo());
        }
        return true;
    }

    /**
     * Match a while statement with its corresponding end statement
     *
     * @param address Address of the while statement
     * @return Address of the end statement
     */
    private int findWhileEnd(int address) {
        ArrayList<Statement> statements = bbp.getStatements();
        int depth = 0;
        for (int i = address - 1; i < statements.size(); i++) {
            switch (statements.get(i).getOperation()) {
                case "while":
                    depth++;
                    break;
                case "end":
                    depth--;
                    if (depth == 0) {
                        return i;
                    }
                    break;
                default:
                    break;
            }
        }
        return -1;
    }
    /**
     * Get the named variable or create it if it was not found
     * @param name Name of the variable
     * @return The named variable
     */
    private BBVariable getVariable(String name){
        boolean found = false;
        BBVariable var = null;
        for (BBVariable v : variables) {
            if (v.getName().equals(name)) {
                var = v;
                found = true;
                break;
            }
        }
        if (!found) {
            var = new BBVariable(name);
            variables.add(var);
        }
        return var;
    }
}
