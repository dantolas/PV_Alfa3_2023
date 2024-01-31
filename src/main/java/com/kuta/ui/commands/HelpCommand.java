package com.kuta.ui.commands;

import com.kuta.ui.CommandHandler;

/**
 * HelpCommand
 */
public class HelpCommand implements CCommand{

    CommandHandler handler;
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
        handler.getCui().printSeparatorLine();
        String helpString =
        """
        |help| -> Lists all commands, or provides further information on specific commands.
        |clear| -> Clears the console.

        |exit|
        """;
        

        handler.getCui().printSeparatorLine();
    }

    
}
