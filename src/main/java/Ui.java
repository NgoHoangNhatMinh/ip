import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println("LUX: Hello! I'm LUX, your personal assistant chatbot.");
        System.out.println("LUX: How can I assist you today?");
        showLine();
    }

    public void showBye() {
        System.out.println("LUX: Goodbye! Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.err.println("LUX: Loading error");
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
        return DateTimeFormatter.ofPattern("yyyy-MM-dd:HHmm");
    }
}
