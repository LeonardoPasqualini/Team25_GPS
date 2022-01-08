package logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Schedule {
    private List<Classroom> classrooms = new ArrayList<>();

    public List<ClassDate> viewSchedule(String className){
        for (var classroom: classrooms) {
            if(classroom.getName().equalsIgnoreCase(className)){
                return classroom.getClasses();
            }
        }
        return null;
    }

    public boolean addClassroom(String name, Calendar begin, Calendar end){
        for (var classroom: classrooms) {
            if(classroom.getName().equalsIgnoreCase(name)){
                classroom.addClass(new ClassDate(begin,end));
                return true;
            }
        }
        return false;
    }

    public void createClassroom(String name){
        classrooms.add(new Classroom(name, 0,0,0));
    }
}