package com.kuta.ui.commands;

import java.io.PrintStream;

/**
 * ClearCommand
 */
public class ClearCommand implements CCommand{

    private PrintStream output;
    

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
        output.print("\033[H\033[2J");
        output.flush();
    }

    
}
