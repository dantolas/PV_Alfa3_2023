package com.kuta.ui.commands;

/**
 * Interface that all commands should implement
 */
public interface CCommand {

    /**
     * Returns command name
     * @return Command name
     */
    public String getName();

    /**
     * Return short description and usage help for command
     * @return
     */
    public String getHelp();

    /**
     * Do all the work the command has to do here
     * @param args
     */
    public void  execute(String... args);

}
