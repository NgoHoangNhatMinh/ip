public class UnmarkCommand extends Command {
    private String argument;

    public UnmarkCommand(String argument) {
        this.argument = argument;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws LuxException {
        try {
            int idx = Integer.parseInt(argument);
            if (idx < 0 || idx >= tasks.getTasks().size()) {
                throw new LuxException("Please specify the task number you want to unmark.");
            }
            tasks.getTasks().get(idx).unmark();
            ui.unmarkTask(tasks.getTasks().get(idx));
        } catch (NumberFormatException e) {
            throw new LuxException(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
