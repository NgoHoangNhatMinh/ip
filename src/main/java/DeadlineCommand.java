import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private String arguments;

    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws LuxException {
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new LuxException("Please specify the deadline using /by.");
        }
        String description = parts[0].trim();

        try {
            LocalDateTime by = LocalDateTime.parse(parts[1].trim(), ui.getTimeFormatter());
            DeadlineTask task = new DeadlineTask(description, by);
            tasks.addTasks(task);
            ui.addDeadline(task);
        } catch (DateTimeParseException e) {
            throw new LuxException(
                    "Error: Invalid date/time format. Please follow this format: " + ui.getTimeFormatter());
        }

    }

    public boolean isExit() {
        return false;
    }
}