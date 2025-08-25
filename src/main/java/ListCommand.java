public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
