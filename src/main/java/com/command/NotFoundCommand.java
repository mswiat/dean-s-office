package com.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotFoundCommand implements ICommand{
    private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundCommand.class);
    @Override
    public void execute() {
        LOGGER.info("Command not found");
    }
}
