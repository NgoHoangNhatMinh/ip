package lux.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import lux.data.EventTask;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Add a new event task
 */
public class EventCommand extends Command {
    private String arguments;

    public EventCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Parse command's argument into description, from time, and to time based on
     * "/from" and "/to" keyword
     * Add event task to the list of tasks
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LuxException {
        String[] fromSplit = arguments.split(" /from ", 2);
        if (fromSplit.length < 2) {
            throw new LuxException(
                    "Please follow this format: todo {description} /from {HHmm dd-MM-yyyy} /to {HHmm dd-MM-yyyy}");
        }

        String description = fromSplit[0].trim();
        String[] toSplit = fromSplit[1].split(" /to ", 2);
        if (toSplit.length < 2) {
            throw new LuxException(
                    "Please follow this format: todo {description} /from {HHmm dd-MM-yyyy} /to {HHmm dd-MM-yyyy}");
        }

        try {
            LocalDateTime from = LocalDateTime.parse(toSplit[0].trim(), ui.getTimeFormatter());
            LocalDateTime to = LocalDateTime.parse(toSplit[1].trim(), ui.getTimeFormatter());
            EventTask task = new EventTask(description, from, to);

            tasks.addTasks(task);
            return ui.addEvent(task);
        } catch (DateTimeParseException e) {
            throw new LuxException(
                    "Error: Invalid date/time format. Please follow this format: {HHmm dd-MM-yyyy}");
        }

    }

    public boolean isExit() {
        return false;
    }
}
