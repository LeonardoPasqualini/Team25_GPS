package logic;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextUI {
    static Schedule schedule = new Schedule();
    static TextUI textUi = new TextUI();

    public static final String newLine = "_______________________________________________";

    public static void main(String[] args) {

        populateBaseSchedule();

        Calendar b1 = Calendar.getInstance();
        Calendar e1 = Calendar.getInstance();
        Calendar b2 = Calendar.getInstance();
        Calendar e2 = Calendar.getInstance();
        Calendar b3 = Calendar.getInstance();
        Calendar e3 = Calendar.getInstance();

        e1.add(Calendar.HOUR, 2);
        b2.add(Calendar.HOUR, 4);
        e2.add(Calendar.HOUR, 6);
        b3.add(Calendar.HOUR, 8);
        e3.add(Calendar.HOUR, 10);



        schedule.addClassroom("A1.1", b1, e1);
        schedule.addClassroom("A1.1", b2, e2);
        schedule.addClassroom("A1.1", b3, e3);
        schedule.addClassroom("A2.1", b1, e1);
        schedule.addClassroom("A2.2", b3, e3);




        textUi.mainMenu();

    }

    public static boolean populateBaseSchedule(){
        JSONParser parser = new JSONParser();

        // parse JSON file
        try {
            Object object = parser.parse(new FileReader("src/logic/deis_schedule.json"));

            // typecast obk to JSONObject
            JSONObject jsonObject = (JSONObject) object;

            JSONArray classroomsList = (JSONArray)jsonObject.get("classrooms");



            // iterating classrooms
            Iterator<JSONObject> it = classroomsList.iterator();
            while (it.hasNext()){
                JSONObject actual = it.next();
                String name = (String) actual.get("name");
                long projector = (long) actual.get("projector");
                long capacity = (long) actual.get("capacity");
                long computers = (long) actual.get("computers");

                // TODO CREATE CLASSROOM
                System.out.printf("\nClassroom %s, projector %d, capacity %d, computers %d.\n", name, projector, capacity, computers);
                schedule.createClassroom(name, (int)projector, (int)capacity, (int)computers);

                // TODO ADD CLASSES
                JSONObject actualClasses = (JSONObject)actual.get("classes");

                // MONDAY
                System.out.printf("\tMonday\n");
                JSONArray mondayList = (JSONArray)actualClasses.get("monday");
                Iterator<JSONObject> itMonday = mondayList.iterator();
                while (itMonday.hasNext()){
                    JSONObject actualMonday = itMonday.next();
                    String start = (String) actualMonday.get("start");
                    String end = (String) actualMonday.get("end");
                    System.out.printf("\t\t%s - %s\n", start, end);
                }

                // TUESDAY
                System.out.printf("\tTuesday\n");
                JSONArray tuesdayList = (JSONArray)actualClasses.get("tuesday");
                Iterator<JSONObject> itTuesday = tuesdayList.iterator();
                while (itTuesday.hasNext()){
                    JSONObject actualMonday = itTuesday.next();
                    String start = (String) actualMonday.get("start");
                    String end = (String) actualMonday.get("end");
                    System.out.printf("\t\t%s - %s\n", start, end);
                }

                // WEDNESDAY
                System.out.printf("\tWednesday\n");
                JSONArray wednesdayList = (JSONArray)actualClasses.get("wednesday");
                Iterator<JSONObject> itWednesday = wednesdayList.iterator();
                while (itWednesday.hasNext()){
                    JSONObject actualMonday = itWednesday.next();
                    String start = (String) actualMonday.get("start");
                    String end = (String) actualMonday.get("end");
                    System.out.printf("\t\t%s - %s\n", start, end);
                }

                // THURSDAY
                System.out.printf("\tThursday\n");
                JSONArray thursdayList = (JSONArray)actualClasses.get("thursday");
                Iterator<JSONObject> itThursday = thursdayList.iterator();
                while (itThursday.hasNext()){
                    JSONObject actualMonday = itThursday.next();
                    String start = (String) actualMonday.get("start");
                    String end = (String) actualMonday.get("end");
                    System.out.printf("\t\t%s - %s\n", start, end);
                }

                // FRIDAY
                System.out.printf("\tFriday\n");
                JSONArray fridayList = (JSONArray)actualClasses.get("friday");
                Iterator<JSONObject> itFriday = fridayList.iterator();
                while (itFriday.hasNext()){
                    JSONObject actualMonday = itFriday.next();
                    String start = (String) actualMonday.get("start");
                    String end = (String) actualMonday.get("end");
                    System.out.printf("\t\t%s - %s\n", start, end);
                }

            }
        }
        catch (IOException e) {
            System.err.println("I/O exception: " + e);
        }
        catch (ParseException e) {
            System.err.println("Error parson JSON file");
        }


        return false;
    }

    /**
     * Main menu (Mockup-1)
     */
    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            // clear console
            clearConsole();

            // title
            System.out.printf("\n\tMain menu\n%s\n\n", newLine);

            // available options
            System.out.print("\t1-View Schedule\n\t2-View schedule by parameters\n\t3-Fast schedule\n\t4-Remove schedule\n\t5-Remove base schedule\n\n\t0-Exit\n\n");

            // user input
            System.out.print("\tEnter your choice: ");

            // get user input
            input = scanner.nextLine();

            switch (input) {
                case "1" -> viewScheduleMenu();
                case "2" -> viewScheduleByParametersMenu();
                case "3" -> fastScheduleMenu();
                case "4" -> removeScheduleMenu();
                case "5" -> removeBaseScheduleMenu();
                case "0" -> System.out.println("The software will now exit.");
                default -> System.out.println("Invalid option.");
            }
            scanner.nextLine();
        } while (!input.equals("0"));
    }


    /**
     * View Schedule menu(Mockup-2)
     */
    public void viewScheduleMenu(){
        Scanner scanner = new Scanner(System.in);
        String input;
        // title
        System.out.printf("\n\tView schedule\n%s\n\n", newLine);

        // user input
        System.out.print("\tEnter the classroom name or press enter for global view: ");
        input = scanner.nextLine();

        // print classroom data
        System.out.print(schedule.viewSchedule(input));
    }

    /**
     * View schedule by parameters (Mockup-3)
     */
    public void viewScheduleByParametersMenu(){
        Scanner scanner = new Scanner(System.in);
        String date, start, end, capacityStr, computersStr, projectorStr;
        int capacity, computers, projector;
        int yearB, dayB, monthB, hourB, minuteB;
        int yearE, dayE, monthE, hourE, minuteE;
        Calendar calendarBegin = Calendar.getInstance(), calendarEnd = Calendar.getInstance();
        List<Classroom> classroom;

        // title
        System.out.printf("\n\tView schedule by parameters\n%s\n\n", newLine);

        // user input
        System.out.printf("\tEnter the day (yyyy-MM-dd): ");
        date = scanner.nextLine();

        System.out.printf("\n\tEnter start time: ");
        start = scanner.nextLine();

        System.out.printf("\n\tEnter end time: ");
        end = scanner.nextLine();

        System.out.printf("\n\tEnter minimum capacity: ");
        capacityStr = scanner.nextLine();

        System.out.printf("\n\tNumber of computers: ");
        computersStr = scanner.nextLine();

        System.out.printf("\n\tProjector (yes/no): ");
        projectorStr = scanner.nextLine();

        // convert str to calendar
        // public final void set(int year,int month,int day,int hourOfDay, int minute,int second)
        String[] dates = date.split("-");
        if (dates.length != 3){
            System.out.println("Error! The input entered for day field must be: yyyy-MM-dd");
            return;
        }

        String[] hourBegin = start.split(":");
        if (hourBegin.length != 2){
            System.out.println("Error! The input entered for start time must be: HH:mm");
            return;
        }

        String[] hourEnd = end.split(":");
        if (hourEnd.length != 2){
            System.out.println("Error! The input entered for end time must be: HH:mm");
            return;
        }

        // convert str to int
        try {
            capacity = Integer.parseInt(capacityStr);
            computers = Integer.parseInt(computersStr);
            projector = Integer.parseInt(projectorStr);

            // begin
            yearB = Integer.parseInt(dates[0]);
            monthB = Integer.parseInt(dates[1]);
            dayB = Integer.parseInt(dates[2]);
            hourB = Integer.parseInt(hourBegin[0]);
            minuteB = Integer.parseInt(hourBegin[1]);

            // end
            yearE = Integer.parseInt(dates[0]);
            monthE = Integer.parseInt(dates[1]);
            dayE = Integer.parseInt(dates[2]);
            hourE = Integer.parseInt(hourEnd[0]);
            minuteE = Integer.parseInt(hourEnd[1]);
        }
        catch (NumberFormatException ex){
            System.out.println("Error! The input entered is not valid!");
            return;
        }

        // set begin calendar
        calendarBegin.set(yearB, monthB-1, dayB, hourB, minuteB);

        // set end calendar
        calendarEnd.set(yearE, monthE-1, dayE, hourE, minuteE);

        // search
        classroom = schedule.viewScheduleByParameters(calendarBegin, calendarEnd, capacity, computers, projector);

        // print available classrooms that match criteria
        System.out.printf("\n\n\tThere are %d schedules matching your criteria.\n", classroom.size());
        for(Classroom temp: classroom){
            System.out.printf("\t\t%s\n", temp.getName());
        }

        System.out.printf("\n\t<press any key to return to the main menu>\n");
    }

    /**
     * Remove schedule (Mockup-4)
     */
    public void removeScheduleMenu(){
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
    }

    /**
     * Remove base schedule (Mockup-5)
     */
    public void removeBaseScheduleMenu(){
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
    }

    /**
     * Fast schedule (Mockup-6)
     */
    public void fastScheduleMenu(){
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
    }

    /**
     * Clear the console
     */
    public void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
