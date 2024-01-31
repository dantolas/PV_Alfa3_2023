package com.kuta.ui.commands;

import java.io.PrintStream;

/**
 * CCommand
 */
public interface CCommand {

    public String getName();

    public String getHelp();

    public void  execute(String... args);

}
