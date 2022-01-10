package test;

import static org.junit.Assert.*;
import logic.*;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

public class TestClassroom {
    private Classroom classroom = new Classroom("Classroom A1.2", 1, 24, 23);


    @Test
    public void testGetClasses(){
        List<ClassDate> list = classroom.getClasses();

        assertEquals(list, classroom.getClasses());
    }

    @Test
    public void testGetName(){

        assertEquals("Classroom A1.2", classroom.getName());
    }

    @Test
    public void testAddClass(){
        int size = classroom.getClasses().size();

        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.HOUR, 2);

        ClassDate newClass = new ClassDate(begin, end, false);

        classroom.addClass(newClass);


        assertEquals(size+1, classroom.getClasses().size());
    }

    @Test
    public void testRemoveClass(){
        int size;
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

        ClassDate firstClass = new ClassDate(b1, e1, false);
        ClassDate secondClass = new ClassDate(b2, e2, false);
        ClassDate thirdClass = new ClassDate(b3, e3, false);

        classroom.addClass(firstClass);
        classroom.addClass(secondClass);
        classroom.addClass(thirdClass);

        size = classroom.getClasses().size();

        classroom.removeClass(thirdClass);

        assertEquals(size-1, classroom.getClasses().size());
    }


}
