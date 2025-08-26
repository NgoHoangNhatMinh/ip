package lux.parser;

import java.util.HashSet;
import java.util.Set;

import lux.commands.ByeCommand;
import lux.commands.Command;
import lux.commands.DeadlineCommand;
import lux.commands.DeleteCommand;
import lux.commands.EventCommand;
import lux.commands.ListCommand;
import lux.commands.MarkCommand;
import lux.commands.TodoCommand;
import lux.commands.UnmarkCommand;
import lux.exception.LuxException;

/**
 * This class parses user's input and return commands accordingly
 */
public class Parser {

    /**
     * parse user's input into appropriate command
     * 
     * @param fullCommand raw string of user input
     * @return specific command based on the first word of user's input
     * @throws LuxException
     */
    public static Command parse(String fullCommand) throws LuxException {
        String[] parts = fullCommand.trim().split("\\s+", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";
        switch (commandWord) {
            case "list":
                return new ListCommand();
            case "todo":
                return new TodoCommand(arguments);
            case "deadline":
                return new DeadlineCommand(arguments);
            case "event":
                return new EventCommand(arguments);
            case "mark":
                return new MarkCommand(arguments);
            case "unmark":
                return new UnmarkCommand(arguments);
            case "delete":
                return new DeleteCommand(arguments);
            case "bye":
                return new ByeCommand();
            default:
                throw new LuxException("Unknown command: " + commandWord);
        }
    }
}
