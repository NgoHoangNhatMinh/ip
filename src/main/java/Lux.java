import java.util.Scanner;

public class Lux {
    public static void main(String[] args) {
        String greeting = "-------------------------------\n"
                + "Hello! I'm LUX\n"
                + "What can I do for you?\n"
                + "-------------------------------\n";
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            System.out.println("-------------------------------");
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                scanner.close();
                return;
            }
            System.out.println(command);
            System.out.println("-------------------------------");
        }
    }
}
