package lux.ui;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import lux.data.Task;
import lux.data.TaskList;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm LUX, your personal assistant chatbot.");
        System.out.println("How can I assist you today?");
        showLine();
    }

    public void listTasks(TaskList tasks) {
        System.out.println(tasks);
    }

    public void addTodo(Task task) {
        System.out.println("Added a new deadline task:\n    " + task);
    }

    public void addDeadline(Task task) {
        System.out.println("Added a new deadline task:\n    " + task);
    }

    public void addEvent(Task task) {
        System.out.println("Added a new event task:\n    " + task);
    }

    public void deleteTask(Task task) {
        System.out.println("I've deleted this task");
        System.out.println("    " + task);
    }

    public void markTask(Task task) {
        System.out.println("Great! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public void unmarkTask(Task task) {
        System.out.println("I've unmarked this task:");
        System.out.println("    " + task);
    }

    public void showBye() {
        System.out.println("Goodbye! Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.err.println("Loading error");
    }

    public void showLine() {
        System.out.println("========================================");
    }

    public void showError(String e) {
        System.err.println(e);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public DateTimeFormatter getTimeFormatter() {
        return DateTimeFormatter.ofPattern("HHmm dd-MM-yyyy");
    }
}
