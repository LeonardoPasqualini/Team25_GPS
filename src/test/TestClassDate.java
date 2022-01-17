package test;

import logic.ClassDate;
import logic.Classroom;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestClassDate {
    Calendar now = Calendar.getInstance();
    Calendar more2h = Calendar.getInstance();




    @Test
    public void testGetBegin(){
        more2h.add(Calendar.HOUR, 2);
        now.set(2022,0,17,18,20,50);
        ClassDate classDate = new ClassDate(now,
                more2h,
                false);

        assertEquals(now, classDate.getBegin());
    }

    @Test
    public void testGetEnd(){
        more2h.add(Calendar.HOUR, 2);
        now.set(2022,0,17,18,20,50);
        ClassDate classDate = new ClassDate(now,
                more2h,
                false);

        assertEquals(more2h, classDate.getEnd());
    }

    @Test
    public void testGetIsBase(){
        more2h.add(Calendar.HOUR, 2);
        now.set(2022,0,17,18,20,50);
        ClassDate classDate = new ClassDate(now,
                more2h,
                false);

        assertFalse(classDate.getIsBase());
    }

}
