package com.kuta.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import com.kuta.ui.commands.CCommand;

/**
 * ConsoleUI
 */
public class ConsoleUI {

    final String INPUT_INDICATOR = ">>> ";
    
    String separator;
    int separatorLineLength;
    PrintStream outputStream;
    InputStream inputStream;
    Scanner reader;

    public void printSeparatorLine(){

        StringBuilder line = new StringBuilder();
        for (int i = 0; i < separatorLineLength; i++) {
            line.append(separator);
        }
        outputStream.println(line);
    }

    public void println(String text){
        outputStream.println(text);
    }

    public void print(String text){
        outputStream.print(text);
    }

    public void printf(String text,Object... args){
        outputStream.printf(text,args);
    }

    public int readInputInt(){
        while(true){
            print(INPUT_INDICATOR);
            try {

                return reader.nextInt();
                
            } catch (Exception e) {
            }
            println("Please enter a number.");
        }
    }

    public String readInputString(){

        while(true){
            print(INPUT_INDICATOR);
            try {

                return reader.nextLine();
                
            } catch (Exception e) {
            }
            println("Please enter valid text.");
        }
    }

}
