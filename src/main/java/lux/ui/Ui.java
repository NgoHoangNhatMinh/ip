package lux.ui;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import lux.data.Task;
import lux.data.TaskList;

/**
 * This class handle UI behaviors
 */
public class Ui {
    private Scanner scanner;

    /**
     * Create a UI instance with scanner for reading user's input
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Print welcome
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm LUX, your personal assistant chatbot.");
        System.out.println("How can I assist you today?");
        showLine();
    }

    /**
     * Print list task
     * @param tasks
     */
    public void listTasks(TaskList tasks) {
        System.out.println(tasks);
    }

    /**
     * Print add todo
     * @param task
     */
    public void addTodo(Task task) {
        System.out.println("Added a new deadline task:\n    " + task);
    }

    /**
     * Print add deadline
     * @param task
     */
    public void addDeadline(Task task) {
        System.out.println("Added a new deadline task:\n    " + task);
    }

    /**
     * Print add event
     * @param task
     */
    public void addEvent(Task task) {
        System.out.println("Added a new event task:\n    " + task);
    }

    /**
     * Print deleted task
     * @param task
     */
    public void deleteTask(Task task) {
        System.out.println("I've deleted this task");
        System.out.println("    " + task);
    }

    /**
     * Print mark task
     * @param task
     */
    public void markTask(Task task) {
        System.out.println("Great! I've marked this task as done:");
        System.out.println("    " + task);
    }

    /**
     * Print unmark task
     * @param task
     */
    public void unmarkTask(Task task) {
        System.out.println("I've unmarked this task:");
        System.out.println("    " + task);
    }

    /**
     * Print bye
     */
    public void showBye() {
        System.out.println("Goodbye! Hope to see you again soon!");
    }

    /**
     * Print found task
     * @param tasks
     */
    public void findTasks(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list: ");
        listTasks(tasks);
    }

    /**
     * Print loading error
     */
    public void showLoadingError() {
        System.err.println("Loading error");
    }

    /**
     * Print dividing line
     */
    public void showLine() {
        System.out.println("========================================");
    }

    /**
     * Print generic error
     * @param e
     */
    public void showError(String e) {
        System.err.println(e);
    }

    /**
     * Read user's input
     * @return
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Format datetime according to pattern
     * @return
     */
    public DateTimeFormatter getTimeFormatter() {
        return DateTimeFormatter.ofPattern("HHmm dd-MM-yyyy");
    }
}
