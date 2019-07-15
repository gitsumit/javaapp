package HMS;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class hmsDateValidator {

    private static final String FORMATTED="dd/MM/yyyy";

    //constructor
    public hmsDateValidator(){}

    /**
     *
     */
    public String validate(final String date) throws ParseException{
        SimpleDateFormat dateFormat= new SimpleDateFormat(FORMATTED);
        Date date2=null;
        try {
            date2=dateFormat.parse(date);
        }
        catch (ParseException e){
            throw e;
        }
        return dateFormat.format(date2);
    }

    public boolean CompareDate(String currentDate, String inputDate) throws ParseException
    {

        SimpleDateFormat dateformat = new SimpleDateFormat(FORMATTED);
        Date d1 = dateformat.parse(currentDate);
        Date d2 = dateformat.parse(inputDate);

        if (d1.compareTo(d2) > 0) {
            return false;
        } else if (currentDate.compareTo(inputDate) < 0) {
            return true;
        } else if (currentDate.compareTo(inputDate) == 0) {
            return true;
        } else {
            return false;
        }
    }
}
