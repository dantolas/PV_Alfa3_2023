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
    ⠀⠀⠀⠀⠈⠉⠉⠉⠛⠛⠛⠛⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀(Had no idea what a welcome message should look like don't judge me)⠀
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


    public ConsoleUI(String separator, int separatorLineLength, PrintStream outputStream, InputStream inputStream) {
        this.separator = separator;
        this.separatorLineLength = separatorLineLength;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        reader = new Scanner(inputStream);
    }

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
        int number = 0;
        while(true){
            print(INPUT_INDICATOR);
            try {

                String input = reader.nextLine();
                number = Integer.parseInt(input);
                break;
                
            } catch (Exception e) {
                println("Please enter a number.");
            }
        }
        return number;
    }
    public int readInputInt(int max){
        int number = 0;
        while(true){
            print(INPUT_INDICATOR);
            try {

                String input = reader.nextLine();
                number = Integer.parseInt(input);
                if(number <= max)break;
                println("Number must up to "+max);
                
            } catch (Exception e) {
                println("Please enter a number.");
            }
        }
        return number;
    }
    public int readInputInt(int min, int max){
        int number = 0;
        while(true){
            print(INPUT_INDICATOR);
            try {

                String input = reader.nextLine();
                number = Integer.parseInt(input);
                if(number >= min && number >= max)break;
                println("Number must be within "+min+"-"+max);
                
            } catch (Exception e) {
                println("Please enter a number.");
            }
        }
        return number;
    }

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

    public PrintStream getOutputStream(){
        return this.outputStream;
    }

}
