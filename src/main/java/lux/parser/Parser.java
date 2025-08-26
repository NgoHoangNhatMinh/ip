package lux.parser;

import java.util.HashSet;
import java.util.Set;

import lux.commands.ByeCommand;
import lux.commands.Command;
import lux.commands.DeadlineCommand;
import lux.commands.DeleteCommand;
import lux.commands.EventCommand;
import lux.commands.FindCommand;
import lux.commands.ListCommand;
import lux.commands.MarkCommand;
import lux.commands.TodoCommand;
import lux.commands.UnmarkCommand;
import lux.exception.LuxException;

public class Parser {
    private Set<String> keywords;

    public Parser() {
        keywords = new HashSet<>();
        keywords.add("list");
        keywords.add("todo");
        keywords.add("deadline");
        keywords.add("event");
        keywords.add("mark");
        keywords.add("unmark");
        keywords.add("delete");
        keywords.add("bye");
    }

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
            case "find":
                return new FindCommand(arguments);
            default:
                throw new LuxException("Unknown command: " + commandWord);
        }
    }
}
