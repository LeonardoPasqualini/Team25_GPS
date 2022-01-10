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

        if(!populateBaseSchedule()){
            System.out.println("Error populating schedule.");
        }

        textUi.mainMenu();
    }

    public static boolean populateBaseSchedule(){
        JSONParser parser = new JSONParser();
        Calendar calendar = Calendar.getInstance();
        int count = 0, max = 4;

        Calendar nextMonday = getNextDayOfWeek(calendar, Calendar.MONDAY);
        Calendar nextTuesday = getNextDayOfWeek(calendar, Calendar.TUESDAY);
        Calendar nextWednesday = getNextDayOfWeek(calendar, Calendar.WEDNESDAY);
        Calendar nextThursday = getNextDayOfWeek(calendar, Calendar.THURSDAY);
        Calendar nextFriday = getNextDayOfWeek(calendar, Calendar.FRIDAY);

        String[] hourStart;
        String[] hourEnd;
        int classHourBeginTime, classHourEndTime;
        int classMinuteBeginTime, classMinuteEndTime;

        // add base schedule for the next 4 weeks
        while (count < max){

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


                    // CREATE CLASSROOM
                    //System.out.printf("\nClassroom %s, projector %d, capacity %d, computers %d.\n", name, projector, capacity, computers);
                    if (count == 0){
                        schedule.createClassroom(name, (int)projector, (int)capacity, (int)computers);
                    }

                    // TODO ADD CLASSES
                    JSONObject actualClasses = (JSONObject)actual.get("classes");

                    // MONDAY
                    //System.out.printf("\tMonday\n");
                    JSONArray mondayList = (JSONArray)actualClasses.get("monday");
                    Iterator<JSONObject> itMonday = mondayList.iterator();
                    while (itMonday.hasNext()){
                        JSONObject actualMonday = itMonday.next();
                        String start = (String) actualMonday.get("start");
                        String end = (String) actualMonday.get("end");

                        hourStart = start.split(":");
                        hourEnd = end.split(":");
                        classHourBeginTime = Integer.parseInt(hourStart[0]);
                        classMinuteBeginTime = Integer.parseInt(hourStart[1]);
                        classHourEndTime = Integer.parseInt(hourEnd[0]);
                        classMinuteEndTime= Integer.parseInt(hourEnd[1]);

                        Calendar calendarBegin = Calendar.getInstance();
                        calendarBegin.set(nextMonday.get(Calendar.YEAR), nextMonday.get(Calendar.MONTH), nextMonday.get(Calendar.DATE),
                                classHourBeginTime, classMinuteBeginTime);

                        Calendar calendarEnd = Calendar.getInstance();
                        calendarEnd.set(nextMonday.get(Calendar.YEAR), nextMonday.get(Calendar.MONTH), nextMonday.get(Calendar.DATE),
                                classHourEndTime, classMinuteEndTime);

                        schedule.addClassroom(name, calendarBegin, calendarEnd, true);

                        //System.out.printf("\t\t%s - %s\n", start, end);
                    }

                    // TUESDAY
                    //System.out.printf("\tTuesday\n");
                    JSONArray tuesdayList = (JSONArray)actualClasses.get("tuesday");
                    Iterator<JSONObject> itTuesday = tuesdayList.iterator();
                    while (itTuesday.hasNext()){
                        JSONObject actualMonday = itTuesday.next();
                        String start = (String) actualMonday.get("start");
                        String end = (String) actualMonday.get("end");

                        hourStart = start.split(":");
                        hourEnd = end.split(":");
                        classHourBeginTime = Integer.parseInt(hourStart[0]);
                        classMinuteBeginTime = Integer.parseInt(hourStart[1]);
                        classHourEndTime = Integer.parseInt(hourEnd[0]);
                        classMinuteEndTime= Integer.parseInt(hourEnd[1]);

                        Calendar calendarBegin = Calendar.getInstance();
                        calendarBegin.set(nextTuesday.get(Calendar.YEAR), nextTuesday.get(Calendar.MONTH), nextTuesday.get(Calendar.DATE),
                                classHourBeginTime, classMinuteBeginTime);

                        Calendar calendarEnd = Calendar.getInstance();
                        calendarEnd.set(nextTuesday.get(Calendar.YEAR), nextTuesday.get(Calendar.MONTH), nextTuesday.get(Calendar.DATE),
                                classHourEndTime, classMinuteEndTime);

                        schedule.addClassroom(name, calendarBegin, calendarEnd, true);

                        //System.out.printf("\t\t%s - %s\n", start, end);
                    }

                    // WEDNESDAY
                    //System.out.printf("\tWednesday\n");
                    JSONArray wednesdayList = (JSONArray)actualClasses.get("wednesday");
                    Iterator<JSONObject> itWednesday = wednesdayList.iterator();
                    while (itWednesday.hasNext()){
                        JSONObject actualMonday = itWednesday.next();
                        String start = (String) actualMonday.get("start");
                        String end = (String) actualMonday.get("end");

                        hourStart = start.split(":");
                        hourEnd = end.split(":");
                        classHourBeginTime = Integer.parseInt(hourStart[0]);
                        classMinuteBeginTime = Integer.parseInt(hourStart[1]);
                        classHourEndTime = Integer.parseInt(hourEnd[0]);
                        classMinuteEndTime= Integer.parseInt(hourEnd[1]);

                        Calendar calendarBegin = Calendar.getInstance();
                        calendarBegin.set(nextWednesday.get(Calendar.YEAR), nextWednesday.get(Calendar.MONTH), nextWednesday.get(Calendar.DATE),
                                classHourBeginTime, classMinuteBeginTime);

                        Calendar calendarEnd = Calendar.getInstance();
                        calendarEnd.set(nextWednesday.get(Calendar.YEAR), nextWednesday.get(Calendar.MONTH), nextWednesday.get(Calendar.DATE),
                                classHourEndTime, classMinuteEndTime);

                        schedule.addClassroom(name, calendarBegin, calendarEnd, true);

                        //System.out.printf("\t\t%s - %s\n", start, end);
                    }



                    // THURSDAY
                    //System.out.printf("\tThursday\n");
                    JSONArray thursdayList = (JSONArray)actualClasses.get("thursday");
                    Iterator<JSONObject> itThursday = thursdayList.iterator();
                    while (itThursday.hasNext()){
                        JSONObject actualMonday = itThursday.next();
                        String start = (String) actualMonday.get("start");
                        String end = (String) actualMonday.get("end");

                        hourStart = start.split(":");
                        hourEnd = end.split(":");
                        classHourBeginTime = Integer.parseInt(hourStart[0]);
                        classMinuteBeginTime = Integer.parseInt(hourStart[1]);
                        classHourEndTime = Integer.parseInt(hourEnd[0]);
                        classMinuteEndTime= Integer.parseInt(hourEnd[1]);

                        Calendar calendarBegin = Calendar.getInstance();
                        calendarBegin.set(nextThursday.get(Calendar.YEAR), nextThursday.get(Calendar.MONTH), nextThursday.get(Calendar.DATE),
                                classHourBeginTime, classMinuteBeginTime);

                        Calendar calendarEnd = Calendar.getInstance();
                        calendarEnd.set(nextThursday.get(Calendar.YEAR), nextThursday.get(Calendar.MONTH), nextThursday.get(Calendar.DATE),
                                classHourEndTime, classMinuteEndTime);

                        schedule.addClassroom(name, calendarBegin, calendarEnd, true);

                        //System.out.printf("\t\t%s - %s\n", start, end);
                    }


                    // FRIDAY
                    //System.out.printf("\tFriday\n");
                    JSONArray fridayList = (JSONArray)actualClasses.get("friday");
                    Iterator<JSONObject> itFriday = fridayList.iterator();
                    while (itFriday.hasNext()){
                        JSONObject actualMonday = itFriday.next();
                        String start = (String) actualMonday.get("start");
                        String end = (String) actualMonday.get("end");

                        hourStart = start.split(":");
                        hourEnd = end.split(":");
                        classHourBeginTime = Integer.parseInt(hourStart[0]);
                        classMinuteBeginTime = Integer.parseInt(hourStart[1]);
                        classHourEndTime = Integer.parseInt(hourEnd[0]);
                        classMinuteEndTime= Integer.parseInt(hourEnd[1]);

                        Calendar calendarBegin = Calendar.getInstance();
                        calendarBegin.set(nextFriday.get(Calendar.YEAR), nextFriday.get(Calendar.MONTH), nextFriday.get(Calendar.DATE),
                                classHourBeginTime, classMinuteBeginTime);

                        Calendar calendarEnd = Calendar.getInstance();
                        calendarEnd.set(nextFriday.get(Calendar.YEAR), nextFriday.get(Calendar.MONTH), nextFriday.get(Calendar.DATE),
                                classHourEndTime, classMinuteEndTime);

                        schedule.addClassroom(name, calendarBegin, calendarEnd, true);

                        //System.out.printf("\t\t%s - %s\n", start, end);
                    }



                }
            }
            catch (IOException e) {
                System.err.println("I/O exception: " + e);
                return false;
            }
            catch (ParseException e) {
                System.err.println("Error parson JSON file");
                return false;
            }

            nextMonday.add(Calendar.DATE, 7);
            nextTuesday.add(Calendar.DATE, 7);
            nextWednesday.add(Calendar.DATE, 7);
            nextThursday.add(Calendar.DATE, 7);
            nextFriday.add(Calendar.DATE, 7);
            count++;
        }

        return true;
    }

    /**
     * Get the next day of weel
     * @param day_of_week
     * @return
     */
    private static Calendar getNextDayOfWeek(Calendar cal, int day_of_week){
        Calendar calendar = cal.getInstance();
        while(calendar.get(Calendar.DAY_OF_WEEK) != day_of_week){
            calendar.add(Calendar.DATE, 1);
        }
        return calendar;
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
                case "0" -> System.out.println("\tThe software will now exit.");
                default -> System.out.println("\tInvalid option.");
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
        if(schedule.viewSchedule(input) == null){
            System.out.println("Classroom not found.");
            return;
        }
        // print classroom data
        System.out.print(schedule.viewSchedule(input));
    }

    /**
     * View schedule by parameters (Mockup-3)
     */
    public void viewScheduleByParametersMenu(){
        Scanner scanner = new Scanner(System.in);
        String date, dateEnd, start, end, capacityStr, computersStr, projectorStr;
        int capacity, computers, projector;
        int yearB, dayB, monthB, hourB, minuteB;
        int yearE, dayE, monthE, hourE, minuteE;
        Calendar calendarBegin = Calendar.getInstance(), calendarEnd = Calendar.getInstance();

        // title
        System.out.printf("\n\tView schedule by parameters\n%s\n\n", newLine);

        // user input
        System.out.print("\tEnter the start day (yyyy-MM-dd): ");
        date = scanner.nextLine();

        System.out.print("\tEnter the end day (yyyy-MM-dd): ");
        dateEnd = scanner.nextLine();

        System.out.print("\tEnter start time: ");
        start = scanner.nextLine();

        System.out.print("\tEnter end time: ");
        end = scanner.nextLine();

        System.out.print("\tEnter minimum capacity: ");
        capacityStr = scanner.nextLine();

        System.out.print("\tNumber of computers: ");
        computersStr = scanner.nextLine();

        System.out.print("\n\tProjectors: ");
        projectorStr = scanner.nextLine();

        // convert str to calendar
        // public final void set(int year,int month,int day,int hourOfDay, int minute,int second)
        String[] dates = null;
        if(!date.equals("")){
            dates = date.split("-");
            if (dates.length != 3){
                System.out.println("Error! The input entered for day field must be: yyyy-MM-dd");
                return;
            }
        }

        String[] datesEnd = null;
        if(!dateEnd.equals("")){
            datesEnd = dateEnd.split("-");
            if (datesEnd.length != 3){
                System.out.println("Error! The input entered for day field must be: yyyy-MM-dd");
                return;
            }
        }

        String[] hourBegin = null;
        if(!start.equals("")){
            hourBegin = start.split(":");
            if (hourBegin.length != 2){
                System.out.println("Error! The input entered for start time must be: HH:mm");
                return;
            }
        }

        String[] hourEnd = null;
        if(!end.equals("")) {
            hourEnd = end.split(":");
            if (hourEnd.length != 2) {
                System.out.println("Error! The input entered for end time must be: HH:mm");
                return;
            }
        }

        // convert str to int
        try {
            if(!capacityStr.equals(""))
                capacity = Integer.parseInt(capacityStr);
            else
                capacity = -1;

            if(!computersStr.equals(""))
                computers = Integer.parseInt(computersStr);
            else
                computers = -1;

            if(!projectorStr.equals(""))
                projector = Integer.parseInt(projectorStr);
            else
                projector = -1;

            // date
            if(dates != null) {
                yearB = Integer.parseInt(dates[0]);
                monthB = Integer.parseInt(dates[1]);
                dayB = Integer.parseInt(dates[2]);
            }
            else {
                yearB = 1900;
                monthB = 1;
                dayB = 1;
            }
            if(datesEnd != null){
                yearE = Integer.parseInt(datesEnd[0]);
                monthE = Integer.parseInt(datesEnd[1]);
                dayE = Integer.parseInt(datesEnd[2]);
            }
            else{
                yearE = 2100;
                monthE = 12;
                dayE = 31;
            }

            // time
            if(hourBegin != null && hourEnd != null) {
                hourB = Integer.parseInt(hourBegin[0]);
                minuteB = Integer.parseInt(hourBegin[1]);
                hourE = Integer.parseInt(hourEnd[0]);
                minuteE = Integer.parseInt(hourEnd[1]);
            }
            else {
                hourB = 0;
                minuteB = 0;
                hourE = 23;
                minuteE = 59;
            }
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
        String output = schedule.viewScheduleByParameters(calendarBegin, calendarEnd, capacity, computers, projector);

        // print available classrooms that match criteria
        System.out.print(output);

        System.out.print("\n\t<press any key to return to the main menu>\n");
    }

    /**
     * Remove schedule (Mockup-4)
     */
    public void removeScheduleMenu(){
        Scanner scanner = new Scanner(System.in);
        String name, dayStr, start, end, indexStr;
        Calendar calendarBegin = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        int year, day, month;
        int hourB, minuteB, hourE, minuteE;
        int index;

        // title
        System.out.printf("\n\tRemove schedule\n%s\n\n", newLine);

        // user input
        System.out.print("\tEnter the classroom name: ");
        name = scanner.nextLine();

        System.out.print("\tEnter the day (yyyy-MM-dd): ");
        dayStr = scanner.nextLine();

        System.out.print("\tEnter start time: ");
        start = scanner.nextLine();

        System.out.print("\tEnter end time: ");
        end = scanner.nextLine();

        // handle input
        if (name.length() < 1){
            System.out.println("Error! The input entered for classroom name cannot be empty.");
            return;
        }

        String[] dates = null;
        if(!dayStr.equals("")){
            dates = dayStr.split("-");
            if (dates.length != 3){
                System.out.println("Error! The input entered for day field must be: yyyy-MM-dd");
                return;
            }
        }

        String[] hourBegin = null;
        if(!start.equals("")){
            hourBegin = start.split(":");
            if (hourBegin.length != 2){
                System.out.println("Error! The input entered for start time must be: HH:mm");
                return;
            }
        }

        String[] hourEnd = null;
        if(!end.equals("")) {
            hourEnd = end.split(":");
            if (hourEnd.length != 2) {
                System.out.println("Error! The input entered for end time must be: HH:mm");
                return;
            }
        }

        // date
        if(dates != null) {
            year = Integer.parseInt(dates[0]);
            month = Integer.parseInt(dates[1]);
            day = Integer.parseInt(dates[2]);
        }
        else {
            year = 1900;
            month = 1;
            day = 1;
        }

        // time
        if(hourBegin != null && hourEnd != null) {
            hourB = Integer.parseInt(hourBegin[0]);
            minuteB = Integer.parseInt(hourBegin[1]);
            hourE = Integer.parseInt(hourEnd[0]);
            minuteE = Integer.parseInt(hourEnd[1]);
        }
        else {
            hourB = 0;
            minuteB = 0;
            hourE = 23;
            minuteE = 59;
        }

        calendarBegin.set(year, month-1, day, hourB, minuteB);
        calendarEnd.set(year, month-1, day, hourE, minuteE);

        // search
        List<ClassDate> classDateList = schedule.getClassesByParameters(name, calendarBegin, calendarEnd);

        System.out.printf("\n\tThere are %d schedules matching your criteria.\n", classDateList.size());
        for (int i = 0; i < classDateList.size(); i++) {
            System.out.printf("\t\t%d. %s\n", (i+1), classDateList.get(i));
        }


        System.out.printf("\n\tInsert schedule index to remove or 0 to return to the main menu: ");
        indexStr = scanner.nextLine();

        if (indexStr.equals("")){
            System.out.println("Error! The input entered for index is invalid!");
            return;
        }

        // convert str to int
        try {
            index = Integer.parseInt(indexStr);
        }
        catch (NumberFormatException e){
            System.out.println("Error! The input entered for index must be a number!");
            return;
        }

        if (index < 0 || index > classDateList.size()){
            System.out.println("Error! The input entered for index must be between 0 and " + classDateList.size() + " !");
            return;
        }

        if (index == 0){
            return;
        }

        // remove schedule by index
        if (schedule.removeClass(name, classDateList.get(index-1))){
            System.out.println("\tClass removed.");
        }
        else {
            System.out.println("\tError! Unable to remove the class.");
        }

        System.out.print("\n\t<press any key to return to the main menu>\n");
    }

    /**
     * Remove base schedule (Mockup-5)
     */
    public void removeBaseScheduleMenu(){
        Scanner scanner = new Scanner(System.in);
        String name, dayStr, start, end, indexStr;
        Calendar calendarBegin = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        int year, day, month;
        int hourB, minuteB, hourE, minuteE;
        int index;
        // title
        System.out.printf("\n\tRemove base schedule\n%s\n\n", newLine);

        // user input
        System.out.print("\tEnter the classroom name: ");
        name = scanner.nextLine();

        System.out.print("\tEnter the day (yyyy-MM-dd): ");
        dayStr = scanner.nextLine();

        System.out.print("\tEnter start time: ");
        start = scanner.nextLine();

        System.out.print("\tEnter end time: ");
        end = scanner.nextLine();

        // handle input
        if (name.length() < 1){
            System.out.println("Error! The input entered for classroom name cannot be empty.");
            return;
        }

        String[] dates = null;
        if(!dayStr.equals("")){
            dates = dayStr.split("-");
            if (dates.length != 3){
                System.out.println("Error! The input entered for day field must be: yyyy-MM-dd");
                return;
            }
        }

        String[] hourBegin = null;
        if(!start.equals("")){
            hourBegin = start.split(":");
            if (hourBegin.length != 2){
                System.out.println("Error! The input entered for start time must be: HH:mm");
                return;
            }
        }

        String[] hourEnd = null;
        if(!end.equals("")) {
            hourEnd = end.split(":");
            if (hourEnd.length != 2) {
                System.out.println("Error! The input entered for end time must be: HH:mm");
                return;
            }
        }

        // date
        if(dates != null) {
            year = Integer.parseInt(dates[0]);
            month = Integer.parseInt(dates[1]);
            day = Integer.parseInt(dates[2]);
        }
        else {
            year = 1900;
            month = 1;
            day = 1;
        }

        // time
        if(hourBegin != null && hourEnd != null) {
            hourB = Integer.parseInt(hourBegin[0]);
            minuteB = Integer.parseInt(hourBegin[1]);
            hourE = Integer.parseInt(hourEnd[0]);
            minuteE = Integer.parseInt(hourEnd[1]);
        }
        else {
            hourB = 0;
            minuteB = 0;
            hourE = 23;
            minuteE = 59;
        }

        calendarBegin.set(year, month-1, day, hourB, minuteB);
        calendarEnd.set(year, month-1, day, hourE, minuteE);

        // search
        List<ClassDate> classDateList = schedule.getClassesByParameters(name, calendarBegin, calendarEnd);

        System.out.printf("\n\tThere are %d schedules matching your criteria.\n", classDateList.size());
        for (int i = 0; i < classDateList.size(); i++) {
            System.out.printf("\t\t%d. %s\n", (i+1), classDateList.get(i));
        }
        System.out.printf("\n\tInsert schedule index to remove or 0 to return to the main menu: ");
        indexStr = scanner.nextLine();

        if (indexStr.equals("")){
            System.out.println("Error! The input entered for index is invalid!");
            return;
        }

        // convert str to int
        try {
            index = Integer.parseInt(indexStr);
        }
        catch (NumberFormatException e){
            System.out.println("Error! The input entered for index must be a number!");
            return;
        }

        if (index < 0 || index > classDateList.size()){
            System.out.println("Error! The input entered for index must be between 0 and " + classDateList.size() + " !");
            return;
        }

        if (index == 0){
            return;
        }

        // remove schedule by index
        if (schedule.removeBaseClass(name, classDateList.get(index-1))){
            System.out.println("\tBase class removed.");
        }
        else {
            System.out.println("\tError! Unable to remove the class.");
        }

        System.out.print("\n\t<press any key to return to the main menu>\n");
    }

    /**
     * Fast schedule (Mockup-6)
     */
    public void fastScheduleMenu(){
        Scanner scanner = new Scanner(System.in);
        String date, start, end, capacityStr, computersStr, projectorStr;
        int capacity, computers, projector;
        int yearB, dayB, monthB, hourB, minuteB;
        int hourE, minuteE;
        Calendar calendarBegin = Calendar.getInstance(), calendarEnd = Calendar.getInstance();

        // title
        System.out.printf("\n\tFast scheduling:\n%s\n\n", newLine);

        // user input
        System.out.print("\tEnter the day (yyyy-MM-dd): ");
        date = scanner.nextLine();

        System.out.print("\n\tEnter start time: ");
        start = scanner.nextLine();

        System.out.print("\n\tEnter end time: ");
        end = scanner.nextLine();

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
        if (hourEnd.length != 2) {
            System.out.println("Error! The input entered for end time must be: HH:mm");
            return;
        }

        System.out.print("\n\tEnter minimum capacity: ");
        capacityStr = scanner.nextLine();

        System.out.print("\n\tNumber of computers: ");
        computersStr = scanner.nextLine();

        System.out.print("\n\tProjectors: ");
        projectorStr = scanner.nextLine();

        try {
            if(!capacityStr.equals(""))
                capacity = Integer.parseInt(capacityStr);
            else
                capacity = -1;

            if(!computersStr.equals(""))
                computers = Integer.parseInt(computersStr);
            else
                computers = -1;

            if(!projectorStr.equals(""))
                projector = Integer.parseInt(projectorStr);
            else
                projector = -1;

            // date
            yearB = Integer.parseInt(dates[0]);
            monthB = Integer.parseInt(dates[1]);
            dayB = Integer.parseInt(dates[2]);

            // time
            hourB = Integer.parseInt(hourBegin[0]);
            minuteB = Integer.parseInt(hourBegin[1]);
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
        calendarEnd.set(yearB, monthB-1, dayB, hourE, minuteE);

        String output = schedule.getAvailableClassrooms(calendarBegin, calendarEnd, capacity, computers, projector);
        System.out.println(output);

        if(output.equals("No available classrooms matching parameters.\n"))
            return;
        System.out.print("Choose a classroom: ");
        String choice = scanner.nextLine();
        if(schedule.addClassroom(choice, calendarBegin, calendarEnd, false))
            System.out.println("Scheduled successfully.\n");
        else
            System.out.println("Invalid name.");
    }

    /**
     * Clear the console
     */
    public void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
