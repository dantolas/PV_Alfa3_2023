package com.kuta.ui.commands;

import com.kuta.ui.ConsoleUI;

/**
 * ClearCommand
 */
public class ClearCommand implements CCommand{

    ConsoleUI ui;

    public ClearCommand(ConsoleUI ui){
        this.ui = ui;
    }
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getHelp() {
        return "Clears the console of text.\n=> do: clear";
    }

    @Override
    public void execute(String... args) {
        if(args.length < 1) {clearOutput(); return;}

        return;
    }

    private final void clearOutput(){
        ui.print("\033[H\033[2J");
        ui.getOutputStream().flush();
    }

    
}
