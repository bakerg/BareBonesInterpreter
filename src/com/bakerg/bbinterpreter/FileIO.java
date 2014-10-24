package com.bakerg.bbinterpreter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Handles file IO
 *
 * @author Geoff
 */
public class FileIO {

    /**
     * Read the file at the provided path
     *
     * @param filepath Location of the file to be read.
     * @return The contents of the file
     * @throws FileNotFoundException
     */
    public static String read(String filepath) throws FileNotFoundException {
        String content = "";
        Scanner sc = new Scanner(new FileInputStream(filepath));
        while (sc.hasNext()) {
            content += sc.nextLine();
        }
        return content;
    }
}
