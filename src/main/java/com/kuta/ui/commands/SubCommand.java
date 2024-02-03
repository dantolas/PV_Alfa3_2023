package com.kuta.ui.commands;

/**
 * Functional interface
 * Used when a command needs to execute a lot of methods, so those
 * methods can be implemented with lambda declaration.
 *
 * Why? I think it looks pretty that's why.
 */
@FunctionalInterface
public interface SubCommand {

    public void work();
}
