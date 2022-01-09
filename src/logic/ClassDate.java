package logic;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        SimpleDateFormat formatData = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatHoras = new SimpleDateFormat("HH:mm");
        String outDate, outBegin, outEnd;
        Date dateBegin = begin.getTime();
        Date dateEnd = end.getTime();

        outDate = formatData.format(dateBegin);
        outBegin = formatHoras.format(dateBegin);
        outEnd = formatHoras.format(dateEnd);
        return ">>>> [" + outDate + ": " +  outBegin + " - " + outEnd + "]";

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
    @Override
    public boolean equals(Object classDate){
        if(!(classDate instanceof ClassDate)) return false;
        return (this.getBegin().getTime().equals(((ClassDate)classDate).getBegin().getTime()) &&
                this.getEnd().getTime().equals(((ClassDate)classDate).getEnd().getTime()));
    }
}


