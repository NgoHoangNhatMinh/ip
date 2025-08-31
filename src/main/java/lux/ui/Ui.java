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
    public String showWelcome() {
        String message = "Hello! I'm LUX, your personal assistant chatbot.\nHow can I assist you today?";
        return message;
    }

    /**
     * Print list task
     *
     * @param tasks
     */
    public String listTasks(TaskList tasks) {
        String message = tasks.toString();
        return message;
    }

    /**
     * Print add todo
     *
     * @param task
     */
    public String addTodo(Task task) {
        String message = "Added a new deadline task:\n    " + task;
        return message;
    }

    /**
     * Print add deadline
     *
     * @param task
     */
    public String addDeadline(Task task) {
        String message = "Added a new deadline task:\n    " + task;
        return message;
    }

    /**
     * Print add event
     *
     * @param task
     */
    public String addEvent(Task task) {
        String message = "Added a new event task:\n    " + task;
        return message;
    }

    /**
     * Print deleted task
     *
     * @param task
     */
    public String deleteTask(Task task) {
        String message = "I've deleted this task\n" + "    " + task;
        return message;
    }

    /**
     * Print mark task
     *
     * @param task
     */
    public String markTask(Task task) {
        String message = "Great! I've marked this task as done:" + "    " + task;
        return message;
    }

    /**
     * Print unmark task
     *
     * @param task
     */
    public String unmarkTask(Task task) {
        String message = "I've unmarked this task:" + "    " + task;
        return message;
    }

    /**
     * Print bye
     */
    public String showBye() {
        String message = "Goodbye! Hope to see you again soon!";
        return message;
    }

    /**
     * Print found task
     *
     * @param tasks
     */
    public String findTasks(TaskList tasks) {
        return "Here are the matching tasks in your list: " + listTasks(tasks);
    }

    /**
     * Print loading error
     */
    public String showLoadingError() {
        return "Loading error";
    }

    /**
     * Print dividing line
     */
    public String showLine() {
        return "========================================";
    }

    /**
     * Read user's input
     *
     * @return
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Format datetime according to pattern
     *
     * @return
     */
    public DateTimeFormatter getTimeFormatter() {
        return DateTimeFormatter.ofPattern("HHmm dd-MM-yyyy");
    }
}
