import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class Lux {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm LUX\nWhat can I do for you?\n-------------------------------\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("* Bye. Hope to see you again soon!\n");
                scanner.close();
                return;
            } else if (command.equals("list")) {
                list(tasks);
            } else {
                Pattern markPattern = Pattern.compile("mark [\\d]+");
                Pattern unmarkPattern = Pattern.compile("unmark [\\d]+");
                if (unmarkPattern.matcher(command).find()) {
                    int index = Integer.parseInt(command.replaceAll("[^\\d]", ""));
                    if (index >= tasks.size()) {
                        System.out.println(String.format("There is no task %d", index));
                    } else {
                        tasks.get(index).unmark();
                    }
                } else if (markPattern.matcher(command).find()) {
                    int index = Integer.parseInt(command.replaceAll("[^\\d]", ""));
                    if (index >= tasks.size()) {
                        System.out.println(String.format("There is no task %d", index));
                    } else {
                        tasks.get(index).mark();
                    }
                } else {
                    tasks.add(new Task(command));
                    System.out.println("* added: " + command);
                }
            }
            System.out.println("------------------------------\n");
        }
    }

    private static void list(List<Task> texts) {
        for (int i = 0; i < texts.size(); i++) {
            String item = String.format("%d. %s", i, texts.get(i));
            System.out.println(item);
        }
    }
}
