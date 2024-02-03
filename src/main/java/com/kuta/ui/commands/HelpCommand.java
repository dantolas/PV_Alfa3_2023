package com.kuta.ui.commands;

import com.kuta.ui.ConsoleUI;

/**
 * HelpCommand
 */
public class HelpCommand implements CCommand{

    ConsoleUI ui;
    public HelpCommand(ConsoleUI ui){
        this.ui = ui;
    }
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return """
        Use this command to view list of commands
        , or to get usage info on other commands.
        do: help <command>
        """;
    }

    @Override
    public void execute(String... args) {
        ui.printSeparatorLine();
        String helpString =
        """
        |report| -> Generate a data report.

        |add| -> Add records to database.
        |read| -> Read records in database.
        |update| -> Update records in database.
        |delete| -> Delete records from database.

        |help| -> Lists all commands, or provides further information on specific commands.
        |clear| -> Clears the console.
        
        |exit|
        """;
        ui.println(helpString);
        

        ui.printSeparatorLine();
    }

    
}
