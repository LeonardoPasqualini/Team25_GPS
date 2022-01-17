package test;

import logic.ClassDate;
import logic.Classroom;
import logic.Schedule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSchedule {
    Schedule schedule = new Schedule();
    Classroom classroom = new Classroom("Classroom A1.2", 1, 24, 23);

    // viewSchedule (String className)
    @Test
    public void TestViewSchedule(){
        // add classroom
        schedule.createClassroom(classroom.getName(), classroom.getProjector(), classroom.getCapacity(), classroom.getComputers());
        // set calendars
        Calendar calendarBegin = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarBegin.set(2022, 0, 17, 9, 0);
        calendarEnd.set(2022, 0, 17, 11, 0);

        // add class to classroom
        schedule.addClassroom("Classroom A1.2", calendarBegin, calendarEnd, false);

        String expectedResult = "\n\n\tSchedule of classroom Classroom A1.2:\n\n\t> [17-01-2022: 09:00 - 11:00]\n";

        // test
        assertEquals(expectedResult, schedule.viewSchedule("Classroom A1.2"));
    }

    // viewScheduleByParameters(Calendar start, Calendar end, int capacity, int computers, int projector){
    @Test
    public void TestViewByParameters(){
        // add classroom
        schedule.createClassroom(classroom.getName(), classroom.getProjector(), classroom.getCapacity(), classroom.getComputers());
        // set calendars
        Calendar calendarBegin = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarBegin.set(2022, 0, 17, 9, 0);
        calendarEnd.set(2022, 0, 17, 11, 0);

        // add class to classroom
        schedule.addClassroom("Classroom A1.2", calendarBegin, calendarEnd, false);

        String expectedResult = "\n\tClassrooms matching parameters:\n\n\n\tClassroom Classroom A1.2 has:\n\t24 seats\n\t1 projectors\n\t23 computers\n\tClass 1: > [17-01-2022: 09:00 - 11:00]\n";

        assertEquals(expectedResult, schedule.viewScheduleByParameters(calendarBegin, calendarEnd, 0, 0, 0));
    }

    // getClassesByParameters (String name, Calendar start, Calendar end)
    @Test
    public void TestGetClassesByParameters(){
        // add classroom
        schedule.createClassroom(classroom.getName(), classroom.getProjector(), classroom.getCapacity(), classroom.getComputers());
        // set calendars
        Calendar calendarBegin = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarBegin.set(2022, 0, 17, 9, 0);
        calendarEnd.set(2022, 0, 17, 11, 0);

        // add class to classroom
        schedule.addClassroom("Classroom A1.2", calendarBegin, calendarEnd, false);

        List<ClassDate> expectedList = new ArrayList<>();
        expectedList.add(new ClassDate(calendarBegin, calendarEnd, false));

        assertEquals(expectedList, schedule.getClassesByParameters(classroom.getName(), calendarBegin, calendarEnd));
    }

    // addClassroom(String name, Calendar begin, Calendar end, boolean isBase)
    @Test
    public void TestAddClassroom(){
        // add classroom
        schedule.createClassroom(classroom.getName(), classroom.getProjector(), classroom.getCapacity(), classroom.getComputers());
        // set calendars
        Calendar calendarBegin = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarBegin.set(2022, 0, 17, 9, 0);
        calendarEnd.set(2022, 0, 17, 11, 0);

        assertEquals(true, schedule.addClassroom("Classroom A1.2", calendarBegin, calendarEnd, false));
    }

    // removeClass(String name, ClassDate classDateToRemove)
    @Test
    public void TestRemoveClass(){
        // add classroom
        schedule.createClassroom(classroom.getName(), classroom.getProjector(), classroom.getCapacity(), classroom.getComputers());
        // set calendars
        Calendar calendarBegin = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarBegin.set(2022, 0, 17, 9, 0);
        calendarEnd.set(2022, 0, 17, 11, 0);

        // add class to classroom
        schedule.addClassroom("Classroom A1.2", calendarBegin, calendarEnd, false);

        // get list of classes
        List<ClassDate> classDateList = schedule.getClassesByParameters(classroom.getName(), calendarBegin, calendarEnd);

        assertEquals(true, schedule.removeClass(classroom.getName(), classDateList.get(0)));
    }

    // removeBaseClass(String name, ClassDate classDateToRemove)
    @Test
    public void TestRemoveBaseClass(){
        // add classroom
        schedule.createClassroom(classroom.getName(), classroom.getProjector(), classroom.getCapacity(), classroom.getComputers());
        // set calendars
        Calendar calendarBegin = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarBegin.set(2022, 0, 17, 9, 0);
        calendarEnd.set(2022, 0, 17, 11, 0);

        // add class to classroom
        schedule.addClassroom("Classroom A1.2", calendarBegin, calendarEnd, true);

        // get list of classes
        List<ClassDate> classDateList = schedule.getClassesByParameters(classroom.getName(), calendarBegin, calendarEnd);

        assertEquals(true, schedule.removeBaseClass(classroom.getName(), classDateList.get(0)));
    }


    // getAvailableClassrooms(Calendar begin, Calendar end, int capacity, int computers, int projector)
    @Test
    public void TestGetAvailableClassrooms(){
        // add classroom
        schedule.createClassroom(classroom.getName(), classroom.getProjector(), classroom.getCapacity(), classroom.getComputers());
        // set calendars
        Calendar calendarBegin = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarBegin.set(2022, 0, 17, 9, 0);
        calendarEnd.set(2022, 0, 17, 11, 0);

        // add class to classroom
        schedule.addClassroom("Classroom A1.2", calendarBegin, calendarEnd, false);

        List<ClassDate> expectedList = new ArrayList<>();
        expectedList.add(new ClassDate(calendarBegin, calendarEnd, false));

        assertEquals(expectedList, schedule.getAvailableClassrooms(calendarBegin, calendarEnd, classroom.getCapacity(), classroom.getComputers(), classroom.getProjector()));
    }

    // createClassroom(String name, int projector, int capacity, int computers)
    @Test
    public void TestCreateClassroom(){
        int size = schedule.getClassrooms().size();
        // add classroom
        schedule.createClassroom(classroom.getName(), classroom.getProjector(), classroom.getCapacity(), classroom.getComputers());
        int newSize = schedule.getClassrooms().size();

        assertEquals((size + 1), newSize);
    }
}
