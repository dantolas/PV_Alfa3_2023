package com.kuta.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class provides command line interface functionality.
 * Provides methods to both read user input and write output.
 */
public class ConsoleUI {

    final String INPUT_INDICATOR = "-> ";
    public final String WELCOME_MESSAGE= 
    """
    ⠀⠀⠀⠀⢀⣀⣀⣀⣤⣤⣤⣤⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀E-Prescription
    ⣠⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣦⣄⠀⠀⠀⠀⠀Management
    ⠙⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠋⠀⠀⠀⠀⠀Application
    ⣿⣶⣤⣄⣉⣉⠙⠛⠛⠛⠛⠛⠛⠋⣉⣉⣠⣤⣶⣿⠀⠀⠀⠀⠀
    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀
    ⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀|Ema|
    ⣄⡉⠛⠻⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⠟⠛⢉⣠⠀⠀⠀⠀⠀
    ⣿⣿⣿⣶⣶⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣶⣶⣿⣿⣿⠀⠀⠀⠀⠀
    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀
    ⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⠀⠀⠀
    ⣶⣤⣈⡉⠛⠛⠻⠿⠿⠿⠿⠿⠿⠟⠛⠛⢉⣁⣤⣶⠀⠀⠀⠀⠀Samuel
    ⣿⣿⣿⣿⣿⣷⣶⣶⣶⣶⣶⣶⣶⣶⣾⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀Kuta
    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀C4b
    ⠙⠻⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠟⠋⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠈⠉⠉⠉⠛⠛⠛⠛⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
    """;
    public final String GOODBYE_MESSAGE =
    """
    |Goodbye|
    """;
    String separator;
    int separatorLineLength;
    PrintStream outputStream;
    InputStream inputStream;
    Scanner reader;


    /**
     * @param separator Character used in line separators
     * @param separatorLineLength Length of line separators
     * @param outputStream Client output stream
     * @param inputStream Client input stream
     */
    public ConsoleUI(String separator, int separatorLineLength, PrintStream outputStream, InputStream inputStream) {
        this.separator = separator;
        this.separatorLineLength = separatorLineLength;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        reader = new Scanner(inputStream);
    }

    /**
     * Prints the visual seperator line to console
     */
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
    
    /**
     * Reads user input until an integer is submitted.
     * Input is read as string and then parsed to avoid dealing with buffer flushing and unconsumed hidden bytes.
     * @return The integer inputed, OR 0 if the user types 'exit'
     */
    public int readInputInt(){
        int number = 0;
        while(true){
            print(INPUT_INDICATOR);
            try {

                String input = reader.nextLine();
                if(input.toLowerCase().equals("exit")) return 0;
                number = Integer.parseInt(input);
                break;
                
            } catch (Exception e) {
                println("Please enter a number.");
            }
        }
        return number;
    }
    /**
     * Reads use input until an integer is submitted. 
     * @param max Maximum integer allowed
     * @return Integer up to max OR max+1 if the user inputs 'exit'
     */
    public int readInputInt(int max){
        int number = 0;
        while(true){
            print(INPUT_INDICATOR);
            try {

                String input = reader.nextLine();
                if(input.toLowerCase().equals("exit")) return max+1;
                number = Integer.parseInt(input);
                if(number <= max)break;
                println("Number must up to "+max);
                
            } catch (Exception e) {
                println("Please enter a number.");
            }
        }
        return number;
    }
    /**
     * Reads user input until an integer is submitted.
     * @param min Minimum value allowed
     * @param max Maximum value allowed
     * @return Integer in the given range OR max+1 if the user inputs 'exit'
     */
    public int readInputInt(int min, int max){
        int number = 0;
        while(true){
            print(INPUT_INDICATOR);
            try {

                String input = reader.nextLine();
                if(input.toLowerCase().equals("exit")) return max+1;
                number = Integer.parseInt(input);
                if(number >= min && number <= max)break;
                println("Number must be within "+min+"-"+max);
                
            } catch (Exception e) {
                println("Please enter a number.");
            }
        }
        return number;
    }

    /**
     * Reads input string from user and returns it
     * @return
     */
    public String readInputString(){
        while(true){
            print(INPUT_INDICATOR);
            try {
                String input = reader.nextLine();
                return input;
            } catch (Exception e) {
                e.printStackTrace();
                println("Please enter valid text.");
            }
        }
    }
    /**
     * Reads input as boolean from the user.
     * It asks for a yes or no confirmation.
     * @return true if user enters yes or false if user enters no
     */
    public boolean readInputBoolean(){
        while(true){
            println("Please enter (Y)es or (N)o.");
            print(INPUT_INDICATOR);
            try {
                String input = reader.nextLine().toLowerCase();
                if(input.startsWith("y")) return true;
                if(input.startsWith("n")) return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public PrintStream getOutputStream(){
        return this.outputStream;
    }

}
