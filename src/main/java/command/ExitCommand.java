package command;

import com.Console;

public class ExitCommand implements ICommand {
    private Console console;

    public ExitCommand(Console console) {
        this.console = console;
    }

    @Override
    public void execute() {
        console.finishConsole();
    }
}
