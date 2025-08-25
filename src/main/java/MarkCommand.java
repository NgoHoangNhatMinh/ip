public class MarkCommand extends Command {
    private String argument;

    public MarkCommand(String argument) {
        this.argument = argument;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws LuxException {
        try {
            int idx = Integer.parseInt(argument);
            if (idx < 0 || idx >= tasks.getTasks().size()) {
                throw new LuxException("Please specify the task number you want to mark.");
            }
            tasks.getTasks().get(idx).mark();
        } catch (NumberFormatException e) {
            throw new LuxException("Please specify the task number you want to mark.");

        }
    }

    public boolean isExit() {
        return false;
    }
}
