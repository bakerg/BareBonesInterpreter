package com.bakerg.bbinterpreter;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Interpreter for Bare Bones code
 *
 * @author Geoff
 */
public class BBInterpreter {

    /**
     * @param args Filepath of Bare Bones source.
     */
    public static void main(String[] args) {
        String program = "";
        if (args.length == 0) {
            System.out.println("Filepath required as argument!");
            return;
        }
        try {
            program = FileIO.read(args[0]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BBInterpreter.class.getName()).log(Level.SEVERE, null, ex);
        }
        BBExecutor bbe = new BBExecutor(program, true);
        try {
            bbe.executeProgram();
        } catch (NoSuchOperationException ex) {
            Logger.getLogger(BBInterpreter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
