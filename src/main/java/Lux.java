import java.util.Scanner;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Lux {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Lux(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LuxException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    // public static void notMain(String[] args) throws LuxException {
    // String filePath = "data/tasks";
    // DateTimeFormatter timeFormatter =
    // DateTimeFormatter.ofPattern("yyyy-MM-dd:HHmm");

    // List<Task> tasks = new ArrayList<>();

    // Scanner scanner = new Scanner(System.in);
    // while (true) {
    // Map<String, String> parsedCommand = null;
    // while (parsedCommand == null) {
    // try {
    // System.out.print("You: ");
    // String userInput = scanner.nextLine();
    // parsedCommand = parseCommand(userInput, keywords);
    // } catch (LuxException e) {
    // System.err.println(e);
    // }
    // }

    // for (Map.Entry<String, String> entry : parsedCommand.entrySet()) {
    // String k = entry.getKey(), v = entry.getValue();
    // if (k.equals("bye")) {
    // System.out.println("LUX: Goodbye! Hope to see you again soon!");
    // System.out.println(divider);
    // scanner.close();

    // return;
    // } else if (k.equals("mark")) {
    // if (v.equals("")) {
    // System.err
    // .println(new LuxException("Please specify the task number you want to mark as
    // done."));
    // break;
    // }
    // int idx = Integer.parseInt(v);
    // if (idx >= 0 && idx < tasks.size()) {
    // tasks.get(idx).mark();
    // System.out.println("LUX: Task " + idx + " marked as done.");
    // } else {
    // System.out.println("LUX: Invalid task number.");
    // }
    // } else if (k.equals("unmark")) {
    // if (v.equals("")) {
    // System.err.println(
    // new LuxException("Please specify the task number you want to mark as not
    // done."));
    // break;
    // }
    // int idx = Integer.parseInt(v);
    // if (idx >= 0 && idx < tasks.size()) {
    // tasks.get(idx).unmark();
    // System.out.println("LUX: Task " + idx + " marked as not done.");
    // } else {
    // System.out.println("LUX: Invalid task number.");
    // }
    // } else if (k.equals("todo")) {
    // if (v.equals("")) {
    // System.err.println(new LuxException("Please provide a description for your
    // todo task."));
    // break;
    // }
    // TodoTask t = new TodoTask(v);
    // tasks.add(t);
    // System.out.println("LUX: Added a new todo task:\n " + t);
    // } else if (k.equals("deadline")) {
    // if (v.equals("")) {
    // System.err.println(new LuxException("Please provide a description for your
    // deadline task."));
    // break;
    // }
    // if (parsedCommand.containsKey("/by")) {
    // try {
    // LocalDateTime time = LocalDateTime.parse(parsedCommand.get("/by"),
    // timeFormatter);
    // DeadlineTask t = new DeadlineTask(v, time);
    // tasks.add(t);
    // System.out.println("LUX: Added a new deadline task:\n " + t);
    // } catch (DateTimeParseException e) {
    // System.err.println(
    // "Error: Invalid date/time format. Please follow this format: " +
    // timeFormatter);
    // }

    // } else {
    // System.err.println(new LuxException("Please specify the deadline using
    // /by."));
    // }
    // } else if (k.equals("event")) {
    // if (v.equals("")) {
    // System.err.println(new LuxException("Please provide a description for your
    // event task."));
    // break;
    // }
    // if (parsedCommand.containsKey("/from") && parsedCommand.containsKey("/to")) {
    // try {
    // LocalDateTime fromTime = LocalDateTime.parse(parsedCommand.get("/from"),
    // timeFormatter);
    // LocalDateTime toTime = LocalDateTime.parse(parsedCommand.get("/to"),
    // timeFormatter);
    // EventTask t = new EventTask(v, fromTime, toTime);
    // tasks.add(t);
    // System.out.println("LUX: Added a new event task:\n " + t);
    // } catch (DateTimeParseException e) {
    // System.err.println(
    // "Error: Invalid date/time format. Please follow this format: " +
    // timeFormatter);
    // }
    // } else {
    // System.err.println(new LuxException("Please specify both /from and /to for
    // your event."));
    // }
    // } else if (k.equals("delete")) {
    // if (v.equals("")) {
    // System.err.println(new LuxException("Please specify the task number you want
    // to delete."));
    // break;
    // }
    // int idx = Integer.parseInt(v);
    // if (idx >= 0 && idx < tasks.size()) {
    // tasks.remove(idx);
    // System.out.println("LUX: Task " + idx + " deleted.");
    // } else {
    // System.out.println("LUX: Invalid task number.");
    // }
    // }
    // }
    // }
    // }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LuxException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Lux("data/tasks").run();
    }
}
