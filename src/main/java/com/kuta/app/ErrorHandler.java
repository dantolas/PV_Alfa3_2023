package com.kuta.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.kuta.app.exceptions.InitException;
import com.kuta.ui.ConsoleUI;

/**
 * ErrorHandler
 */
public class ErrorHandler {

    ConsoleUI ui;

    public ErrorHandler(ConsoleUI ui) {
        this.ui = ui;
    }
    public void handle(InitException e){
        ui.println("|ERROR| Error occured while testing database connection. Please make sure your configuration is valid.");
        ui.println("Print stack trace?");
        if(ui.readInputBoolean()) e.printStackTrace();
    }
    public void handle(SQLException e){
        ui.println("|ERROR| An SQL error has occured. Please check the status of your server, or make sure your queries are valid.");
        ui.println("Print stack trace?");
        if(ui.readInputBoolean()) e.printStackTrace();
    }
    public void handle(Exception e){
        ui.println("|ERROR| An unexpected error has occured.");
        ui.println("Print stack trace?");
        if(ui.readInputBoolean()) e.printStackTrace();
    }
    public void handle(FileNotFoundException e){
        ui.println("|ERROR| A file is missing. Please ensure the file exists.:"+e.getMessage());
        ui.println("Print stack trace?");
        if(ui.readInputBoolean()) e.printStackTrace();
    }
    public void handle(SecurityException e){
        ui.println("|ERROR| Access to file was denied. Please ensure the the file has the right permissions for reading. :"+e.getMessage());
        ui.println("Print stack trace?");
        if(ui.readInputBoolean()) e.printStackTrace();
    }
    public void handle(IOException e){
        ui.println("|ERROR| Error occured while working with the file system. Please try again.:"+e.getMessage());
        ui.println("Print stack trace?");
        if(ui.readInputBoolean()) e.printStackTrace();
    }
}
