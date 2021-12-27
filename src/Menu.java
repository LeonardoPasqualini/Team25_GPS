import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public static final String newLine = "_______________________________________________";

    public static void main(String[] args) {
        mainMenu();
    }

    /**
     * Main menu (Mockup-1)
     */
    private static void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        // clear console
        clearConsole();

        // title
        System.out.printf("\n\tMain menu\n%s\n\n", newLine);

        // available options
        System.out.printf("\t1-View Schedule\n\t2-View schedule by parameters\n\t3-Fast schedule\n\t4-Remove schedule\n\t5-Remove base schedule\n\n\t0-Exit\n\n");

        // user input
        System.out.printf("\tEnter your choice: ");

        // get user input
        String input = scanner.nextLine();

        switch(input){
            case "1":
                viewScheduleMenu(); break;
            case "2":
                viewScheduleByParametersMenu(); break;
            case "3":
                fastScheduleMenu(); break;
            case "4":
                removeScheduleMenu(); break;
            case "5":
                removeBaseScheduleMenu(); break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Invalid option.");
        }
    }


    /**
     * View Schedule menu(Mockup-2)
     */
    private static void viewScheduleMenu(){
        Scanner scanner = new Scanner(System.in);
        // title
        System.out.printf("\n\tView schedule\n%s\n\n", newLine);

        // user input
        System.out.printf("\tEnter the classroom name: ");

        // TODO print classroom data
        System.out.printf("\n\n\tSchedule of classroom %s:\n", "Classroom 1.3");

        System.out.printf("\n\t03-01-2022\n\t\t08:30 - 10:30\n\t\t11:30 - 12:30\n\t\t17:00 - 20:00");

        System.out.printf("\n\n\t04-01-2022\n\t\t18:30 - 19:30");

        String input = scanner.nextLine();
        mainMenu();
    }

    /**
     * View schedule by parameters (Mockup-3)
     */
    private static void viewScheduleByParametersMenu(){
        Scanner scanner = new Scanner(System.in);
        // title
        System.out.printf("\n\tView schedule by parameters\n%s\n\n", newLine);

        // user input
        System.out.printf("\tEnter the day: ");
        System.out.printf("\n\tEnter start time: ");
        System.out.printf("\n\tEnter end time: ");
        System.out.printf("\n\tEnter minimum capacity: ");
        System.out.printf("\n\tNumber of computers: ");
        System.out.printf("\n\tProjector (yes/no): ");

        // TODO print available classrooms that match criteria
        System.out.printf("\n\n\tThere are %d schedules matching your criteria.\n", 2);
        System.out.printf("\t\t-Classroom 1.2\n");
        System.out.printf("\t\t-Classroom 2.3\n");

        System.out.printf("\n\t<press any key to return to the main menu>\n");

        String input = scanner.nextLine();
        mainMenu();
    }

    /**
     * Remove schedule (Mockup-4)
     */
    private static void removeScheduleMenu(){
        Scanner scanner = new Scanner(System.in);
        // title
        System.out.printf("\n\tRemove schedule\n%s\n\n", newLine);

        // user input
        System.out.printf("\tEnter the classroom name: ");
        System.out.printf("\n\tEnter the day: ");
        System.out.printf("\n\tEnter start time: ");
        System.out.printf("\n\tEnter end time: ");

        System.out.printf("\n\n\tThere are %d schedules matching your criteria.\n", 2);
        System.out.printf("\t\t1. 10:30 - 12:30\n");
        System.out.printf("\t\t1. 14:30 - 16:30\n");

        System.out.println("\n\tInsert schedule index to remove or 0 to return to the main menu: ");
        // TODO remove schedule by index

        String input = scanner.nextLine();
        mainMenu();
    }

    /**
     * Remove base schedule (Mockup-5)
     */
    private static void removeBaseScheduleMenu(){
        Scanner scanner = new Scanner(System.in);
        // title
        System.out.printf("\n\tRemove base schedule\n%s\n\n", newLine);

        // user input
        System.out.printf("\tEnter the classroom name: ");
        System.out.printf("\n\tEnter the day: ");
        System.out.printf("\n\tEnter start time: ");
        System.out.printf("\n\tEnter end time: ");

        System.out.printf("\n\n\tThere are %d schedules matching your criteria.\n", 4);
        System.out.printf("\t\t1. 10:30 - 12:30\n");
        System.out.printf("\t\t1. 14:30 - 16:30\n");
        System.out.printf("\t\t1. 16:30 - 18:30\n");
        System.out.printf("\t\t1. 19:00 - 21:00\n");

        System.out.println("\n\tInsert schedule index to remove or 0 to return to the main menu: ");
        // TODO remove base schedule by index

        String input = scanner.nextLine();
        mainMenu();
    }

    /**
     * Fast schedule (Mockup-6)
     */
    private static void fastScheduleMenu(){
        Scanner scanner = new Scanner(System.in);
        // title
        System.out.printf("\n\tFast schedule\n%s\n\n", newLine);

        // user input
        System.out.printf("\tEnter the day: ");
        System.out.printf("\n\tEnter start time: ");
        System.out.printf("\n\tEnter end time: ");
        System.out.printf("\n\tEnter minimum capacity: ");
        System.out.printf("\n\tNumber of computers: ");
        System.out.printf("\n\tProjector (yes/no): ");

        // TODO print available classrooms that match criteria
        System.out.printf("\n\n\tThere are %d schedules matching your criteria.\n", 2);
        System.out.printf("\t\t1. Classroom 1.2\n");
        System.out.printf("\t\t2. Classroom 2.4\n");

        // TODO book the classroom
        System.out.println("\n\tInsert classroom index to book the classroom or 0 to return to the main menu: ");


        System.out.printf("\n\t<press any key to return to the main menu>\n");

        String input = scanner.nextLine();
        mainMenu();
    }

    /**
     * Clear the console
     */
    private static void clearConsole(){

    }

}
