package logic;
import java.util.Calendar;

public class ClassDate {

    private final Calendar begin;
    private final Calendar end;

    /**
     * Constructor
     * @param begin begin time
     * @param end end time
     */
    public ClassDate(Calendar begin, Calendar end){
        this.begin = begin;
        this.end = end;
    }

    /**
     * Get the beginning time
     * @return beginning time
     */
    public Calendar getBegin() {
        return begin;
    }

    /**
     * Get the ending time
     * @return ending time
     */
    public Calendar getEnd() {
        return end;
    }

    // TODO review return
    @Override
    public String toString() {
        return "[" + begin + ":" + end + "]";
    }

    /**
     * Compare beginning time
     * @param dateToCheck beginning time to be compared with
     * @return true if beginning time is equal
     */
    public boolean isBeginDateEqual(Calendar dateToCheck){
        return  (this.getBegin().getTime().equals(dateToCheck.getTime()));
    }

    /**
     * Compare two ClassDate
     * @param classDate ClassDate to be compared with
     * @return true if both are equal
     */
    public boolean isEqual(ClassDate classDate){
        return (this.getBegin().getTime().equals(classDate.getBegin().getTime()) &&
                this.getEnd().getTime().equals(classDate.getEnd().getTime()));
    }
}


