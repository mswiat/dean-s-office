package com.pg.engine.console.command;

import com.pg.engine.console.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExitCommand implements ICommand {
    @Autowired
    private Console console;

    @Override
    public void execute() {
        console.finishConsole();
    }
}
