package logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Schedule {
    private final List<Classroom> classrooms;

    public Schedule(){
        classrooms = new ArrayList<>();
    }

    public String viewSchedule(String className){
        StringBuilder sb = new StringBuilder();
        if(className.equals("")) {
            sb.append("\n\n\tSchedule of classrooms:\n\n");
            for (var classroom: classrooms) {
                sb.append("\t- Classroom ").append(classroom.getName()).append(":");
                for (var cr : classroom.getClasses()) {
                    sb.append("\n\t").append(cr);
                }
                sb.append("\n\n");
            }
            return sb.toString();
        }
        for (var classroom: classrooms) {
            if(classroom.getName().equalsIgnoreCase(className)){
                sb.append("\n\n\tSchedule of classroom ").append(className).append(":\n");
                for (var cr : classroom.getClasses()) {
                    sb.append("\n\t").append(cr);
                }
                sb.append("\n");
                return sb.toString();
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
    public String viewScheduleByParameters(Calendar start, Calendar end, int capacity, int computers, int projector){
        StringBuilder sb = new StringBuilder();
        int count = 0;

        sb.append("\n\tClassrooms matching parameters:\n\n");
        for(Classroom temp:classrooms){
            // check capacity, computers and projector
            if (temp.getCapacity() < capacity && capacity != -1){
                continue;
            }
            if (temp.getProjector() < projector && projector != -1){
                continue;
            }
            if (temp.getComputers() < computers && computers != -1){
                continue;
            }
            if(temp.checkClass(new ClassDate(start, end)))
                continue;
            sb.append("\n\tClassroom ").append(temp.getName()).append(" has:\n");
            sb.append("\t").append(temp.getCapacity()).append(" seats\n");
            sb.append("\t").append(temp.getProjector()).append(" projectors\n");
            sb.append("\t").append(temp.getComputers()).append(" computers\n");
            List<ClassDate> classes = temp.getClasses();
            for(ClassDate classDate: classes){
                Calendar classDateBegin = classDate.getBegin();
                Calendar classDateEnd = classDate.getEnd();

                if (classDateBegin.compareTo(start) >= 0 && classDateBegin.compareTo(end) <= 0){
                    count++;
                    sb.append("\tClass ").append(count).append(": ").append(classDate).append("\n");
                }

                // compara a data
                /*
                if (classDateBegin.compareTo(start) >= 0 && classDateEnd.compareTo(end) <= 0){
                    count++;
                    sb.append("\tClass ").append(count).append(": ").append(classDate).append("\n");
                }

                 */
            }
        }
        return sb.toString();
    }

    public boolean addClassroom(String name, Calendar begin, Calendar end){
        ClassDate cd = new ClassDate(begin,end);
        for (var classroom: classrooms) {
            if(classroom.getName().equalsIgnoreCase(name)){
                if(checkClassDate(classroom, cd)) {
                    classroom.addClass(cd);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkClassDate(Classroom classroom, ClassDate cd){
        return classroom.checkClass(cd);
    }

    public String getAvailableClassrooms(Calendar begin, Calendar end, int capacity, int computers, int projector){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        sb.append("\tAvailable classrooms:\n\n");
        for(Classroom cr : classrooms){
            if (cr.getCapacity() < capacity && capacity != -1){
                continue;
            }
            if (cr.getProjector() < projector && projector != -1){
                continue;
            }
            if (cr.getComputers() < computers && computers != -1){
                continue;
            }
            if(cr.checkClass(new ClassDate(begin,end))){
                count++;
                sb.append("\t- ").append(cr.getName()).append("\n");
            }
        }
        if(count > 0)
            return sb.toString();
        else
            return "No available classrooms matching parameters.\n";
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