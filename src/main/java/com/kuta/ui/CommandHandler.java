package com.kuta.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kuta.app.ApplicationLayer;
import com.kuta.ui.commands.CCommand;
import com.kuta.ui.commands.ClearCommand;
import com.kuta.ui.commands.DeleteCommand;
import com.kuta.ui.commands.HelpCommand;

/**
 * CommandHandler
 */
public class CommandHandler {

    private ConsoleUI cui; 
    List<CCommand> commands;
    private ApplicationLayer appLayer;


    public ConsoleUI getCui(){
        return this.cui;
    }

    public CommandHandler(){
        commands = new ArrayList<>();
    }

    public CommandHandler addCommand(CCommand command){

        commands.add(command);
        return this;
    }


    public void handle(String input){

        String[] cut = input.split("[;\s:-]");
        String command = cut[0];
        String[] args = new String[cut.length - 1];
        if(cut.length > 1){

            for(int i = 1; i < cut.length; i++){
                args[i] = cut[i];
            }
        }

        for (CCommand c : commands) {
            if(!c.getName().equals(command)) continue;
            c.execute(args);
            return;
        }

        cui.println("Command '"+command+"' not recognized");
    }
    public CommandHandler setUi(ConsoleUI ui){
        this.cui = ui;
        return this;
    }

    public CommandHandler addDefaultCommands(){
        this.commands.add(new HelpCommand(this.cui));
        this.commands.add(new ClearCommand(this.cui));
        this.commands.add(new DeleteCommand(this));

        return this;

    }
    public ApplicationLayer getAPI(){
        return this.appLayer;
    }
}
