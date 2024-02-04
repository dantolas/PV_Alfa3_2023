package com.kuta.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kuta.app.ApplicationLayer;
import com.kuta.ui.commands.AddCommand;
import com.kuta.ui.commands.CCommand;
import com.kuta.ui.commands.ClearCommand;
import com.kuta.ui.commands.DeleteCommand;
import com.kuta.ui.commands.HelpCommand;
import com.kuta.ui.commands.ReadCommand;
import com.kuta.ui.commands.ReportCommand;
import com.kuta.ui.commands.UpdateCommand;

/**
 * Handles all command functionality AND serves as the link between 
 * interface tier and application tier
 *
 * Not initialized with constructors, but method chains.
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

    
    /**
     * Add command to the list of commands the handler
     * recognizes;
     * @param command - The command to add to the list of recognized commands.
     * @return - this
     */
    public CommandHandler addCommand(CCommand command){

        commands.add(command);
        return this;
    }

    
    /**
     * Return command from list based on name.
     * @param cName Name of command you're looking for
     * @return
     */
    public CCommand command(String cName){
        for (CCommand c: commands) {
            if(c.getName().equals(cName)) return c;
        }
        return null;
    }

    /**
     * This method handles all user input submitted.
     * It checks the input with commandList and executes the matching command
     * @param input User input
     */
    public void handle(String input){

        String[] cut = input.split("[;\s:-]");
        String command = cut[0];
        String[] args = new String[cut.length - 1];
        if(cut.length > 1){

            for(int i = 1; i < args.length; i++){
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
    /**
     * Set method used in initialization method chain.
     * Set the ConsoleUI
     * @param ui
     * @return
     */
    public CommandHandler setUi(ConsoleUI ui){
        this.cui = ui;
        return this;
    }
    /**
     * Set method used in init method chain.
     * Set the ApplicationLayer
     * @param appLayer
     * @return
     */
    public CommandHandler setAppLayer(ApplicationLayer appLayer){
        this.appLayer = appLayer;
        return this;
    }
    /**
     * Set method used in init method chain
     * Add default commands the handler should deal with
     */
    public CommandHandler addDefaultCommands(){
        this.commands.add(new HelpCommand(this.cui));
        this.commands.add(new ClearCommand(this.cui));
        this.commands.add(new DeleteCommand(this));
        this.commands.add(new UpdateCommand(this));
        this.commands.add(new AddCommand(this));
        this.commands.add(new ReadCommand(this));
        this.commands.add(new ReportCommand(this));

        return this;

    }
    public ApplicationLayer getAPI(){
        return this.appLayer;
    }
}
