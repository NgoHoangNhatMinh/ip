public class TodoCommand extends Command {
    private String argument;

    public TodoCommand(String argument) {
        this.argument = argument;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTasks(new TodoTask(argument));
    }

    public boolean isExit() {
        return false;
    }
}
