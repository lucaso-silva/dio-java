import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class DateMain {

    public static void main(String[] args) {
        //Date - deprecated
        /*
        var date = new Date();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
        DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        System.out.println(formatter.format(date));
        System.out.println(formatter2.format(date));
        **/

        //Calendar
        /*
        var calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm Z");
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1);   // Jan is 0
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        Date date = calendar.getTime();
        System.out.println(formatter.format(date));
        * */

    }
}
