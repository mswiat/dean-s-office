package com.command;

public class NotFoundCommand implements ICommand{
    @Override
    public void execute() {
        System.out.println("Command not found");
    }
}
