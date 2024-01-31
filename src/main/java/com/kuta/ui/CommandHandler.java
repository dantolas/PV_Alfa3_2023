package com.kuta.ui;

import java.util.Arrays;
import java.util.List;

import com.kuta.ui.commands.CCommand;

/**
 * CommandHandler
 */
public class CommandHandler {

    private ConsoleUI cui; 
    List<CCommand> commands;

    public ConsoleUI getCui(){
        return this.cui;
    }

    public CommandHandler addCommand(CCommand command){

        commands.add(command);
        return this;
    }


    public void handle(String input){

        String[] cut = input.split("[;\s:-]");
        String command = cut[0];
        String[] args = new String[cut.length - 1];
        if(cut.length != 1){

            for(int i = 1; i < cut.length; i++){
                args[i] = cut[i];
            }
        }
        for (CCommand c : commands) {
            if(c.getName().equals(command)) continue;

            c.execute(args);
        }
    }
    public CommandHandler setUi(ConsoleUI ui){
        this.cui = ui;
        return this;
    }
}
