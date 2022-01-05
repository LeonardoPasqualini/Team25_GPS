package logic;
import java.util.ArrayList;
import java.util.List;

public class Classroom {
    private List<ClassDate> classes;    // begin and end of class
    private final String name;                // class name
    private final int projector;              // number of projectors
    private final int capacity;               // capacity of the classroom
    private final int computers;              // number of computers
    private List<String> hardware;      // cables, ...

    /**
     * Constructor
     * @param classes list of classes
     * @param name classroom name
     * @param projector number of projectors
     * @param capacity capacity
     * @param computers number of computers
     * @param hardware cables, ...
     */
    public Classroom(List<ClassDate> classes, String name, int projector, int capacity, int computers, List<String> hardware){
        this.classes = classes;
        this.name = name;
        this.projector = projector;
        this.capacity = capacity;
        this.computers = computers;
        this.hardware = hardware;
    }

    /**
     * Constructor
     * @param name classroom name
     * @param projector number of projectors
     * @param capacity capacity
     * @param computers number of computers
     */
    public Classroom(String name, int projector, int capacity, int computers){
        this.classes = new ArrayList<>();
        this.name = name;
        this.projector = projector;
        this.capacity = capacity;
        this.computers = computers;
        this.hardware = new ArrayList<>();
    }

    /**
     * Get the list of classes for this classroom
     * @return list of classes
     */
    public List<ClassDate> getClasses() {
        return classes;
    }

    /**
     * Get the classroom name
     * @return classroom name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the number of projectors
     * @return number of projectors
     */
    public int getProjector() {
        return projector;
    }

    /**
     * Get the classroom capacity
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Get the number of computers
     * @return number of computers
     */
    public int getComputers() {
        return computers;
    }

    /**
     * Get the list of available hardware
     * @return list of hardware
     */
    public List<String> getHardware() {
        return hardware;
    }

    /**
     * Add a new class to the list of classes
     * @param newClass class to add
     * @return true if class is added
     */
    public boolean addClass(ClassDate newClass){
        return classes.add(newClass);
    }

    /**
     * Remove a class from the list of classes
     * @param deadClass class to be removed
     * @return true if class is removed
     */
    public boolean removeClass(ClassDate deadClass){
        boolean isRemoved = false;
        for(ClassDate temp : classes){
            if (temp.isEqual(deadClass)){
                classes.remove(temp);
                isRemoved = true;
                break;
            }
        }
        return isRemoved;
    }
}
