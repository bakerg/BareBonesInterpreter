package com.bakerg.bbinterpreter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Provides functions to interact with Bare Bones statements
 *
 * @author Geoff
 */
public class BBParser {

    private final String bbc;
    private final ArrayList<Statement> statements;
    private int currentStatement = 0;

    /**
     *
     * @param bbcode Bare Bones code to parse
     */
    public BBParser(String bbcode) {
        this.bbc = bbcode;
        statements = new ArrayList();
        for (String s : bbc.split(";")) {
            String[] tokens = s.split(" ");
            ArrayList<String> tokenList = new ArrayList();
            tokenList.addAll(Arrays.asList(tokens));
            tokenList.removeIf(a -> "".equals(a));
            statements.add(new Statement(tokenList));
        }
    }

    /**
     * Get the next statement in the program
     *
     * @return The next Statement
     */
    public Statement nextStatement() {
        Statement st = statements.get(currentStatement);
        currentStatement++;
        return st;
    }

    /**
     * Reset next statement so it points to the first statement in the program.
     */
    public void reset() {
        currentStatement = 0;
    }

    /**
     * Branch to the supplied address
     *
     * @param address Address to branch to
     */
    public void branch(int address) {
        currentStatement = address;
    }

    /**
     * Get the current address
     *
     * @return The current address
     */
    public int getCurrentAddress() {
        return this.currentStatement;
    }

    /**
     * Get whether the program has ended
     *
     * @return True if there is more of the program to execute
     */
    public boolean hasNext() {
        return currentStatement != statements.size();
    }

    /**
     * Get all the statements in this program
     *
     * @return ArrayList of all statements in this program
     */
    public ArrayList<Statement> getStatements() {
        return statements;
    }

}
