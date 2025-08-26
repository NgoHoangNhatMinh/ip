package lux.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import lux.data.DeadlineTask;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

public class DeadlineCommand extends Command {
    private String arguments;

    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Parse command's argument into description and time based on "/by" keyword
     * Add deadline task to the list of tasks
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LuxException {
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new LuxException("Please follow this format: deadline {description} /by {HHmm dd-MM-yyyy}");
        }
        String description = parts[0].trim();

        try {
            LocalDateTime by = LocalDateTime.parse(parts[1].trim(), ui.getTimeFormatter());
            DeadlineTask task = new DeadlineTask(description, by);
            tasks.addTasks(task);
            ui.addDeadline(task);
        } catch (DateTimeParseException e) {
            throw new LuxException(
                    "Error: Invalid date/time format. Please follow this format: {HHmm dd-MM-yyyy}");
        }

    }

    public boolean isExit() {
        return false;
    }
}
