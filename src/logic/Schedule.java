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

    /**
     * Get list of available classrooms that match criteria
     * @param start start time
     * @param end end time
     * @param capacity classroom capacity
     * @param computers number of computers
     * @param projector number of projectors
     * @return List of classrooms that can have no class between start and end and that match criteria
     */
    public List<Classroom> viewScheduleByParameters(Calendar start, Calendar end, int capacity, int computers, int projector){
        List<Classroom> classroomList = new ArrayList<>();
        List<ClassDate> classes = new ArrayList<>();
        boolean isClassAvailable = true;

        for(Classroom temp:classrooms){

            // check capacity, computers and projector
            if (temp.getCapacity() < capacity || temp.getComputers() < computers || temp.getProjector() < projector){
                continue;
            }

            // get calendar instances
            // check classes
            classes = temp.getClasses();

            for(ClassDate classDate: classes){
                Calendar classDateBegin = classDate.getBegin();
                Calendar classDateEnd = classDate.getEnd();

                System.out.println("CLASSROOM => " + temp.getName());
                System.out.println("START => " + start.getTime());
                System.out.println("END => " + end.getTime());
                System.out.println("classDateBegin => " + classDateBegin.getTime());
                System.out.println("classDateEnd => " + classDateEnd.getTime());

                // compara o dia
                if (start.get(Calendar.YEAR) == classDateBegin.get(Calendar.YEAR)
                        && start.get(Calendar.MONTH) == classDateBegin.get(Calendar.MONTH)
                        && start.get(Calendar.DAY_OF_MONTH) == classDateBegin.get(Calendar.DAY_OF_MONTH)){

                    // comparar datas
                    if (start.compareTo(classDateBegin) > 0){
                        //System.out.println("start começa depois da classDateBegin");
                        if (start.compareTo(classDateEnd) > 0){
                            //System.out.println("start começa depois da classDateEnd");
                            // horário válido porque inicio começa depois do inicio e final da class
                            isClassAvailable = true;
                        }
                        else {
                            isClassAvailable = false;
                            break;
                        }
                    }

                    // novo horário começa antes class ter iniciado
                    if (start.compareTo(classDateBegin) < 0)
                    {
                        //System.out.println("start começa antes de classDateBegin");
                        if (end.compareTo(classDateBegin) < 0){
                            //System.out.println("end começa antes de classDateBegin");
                            // horario válido porque inicio e fim começa antes do claddDateBegin
                            isClassAvailable = true;
                        }
                        else {
                            isClassAvailable = false;
                            break;
                        }
                    }
                }
                else {
                    isClassAvailable = true;
                }

                System.out.println("Available => " + isClassAvailable);
                System.out.println("\n");
            }

            if (isClassAvailable){
                classroomList.add(temp);
            }

        }

        return classroomList;
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

    /**
     * Add a new classroom to the list of classrooms
     * @param name classroom name
     * @param projector number of projectors
     * @param capacity capacity of the classroom
     * @param computers number of computers
     */
    public void createClassroom(String name, int projector, int capacity, int computers){
        classrooms.add(new Classroom(name, projector, capacity, computers));
    }
}