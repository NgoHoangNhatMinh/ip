import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Lux {
    public static void main(String[] args) {
        List<String> texts = new ArrayList<>();

        System.out.println("Hello! I'm LUX\nWhat can I do for you?\n-------------------------------\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("* Bye. Hope to see you again soon!\n");
                scanner.close();
                return;
            } else if (command.equals("list")) {
                list(texts);
            } else {
                texts.add(command);
                System.out.println("* added: " + command);
            }
            System.out.println("------------------------------\n");
        }
    }

    private static void list(List<String> texts) {
        for (int i = 0; i < texts.size(); i++) {
            String item = String.format("%d. %s", i, texts.get(i));
            System.out.println(item);
        }
    }
}
