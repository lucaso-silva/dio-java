import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

        //LocalDate
        System.out.println("==== LocalDate ====");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(formatter.format(localDate));

        System.out.println("\nString to date");
        var strDate = "29/08/2025";
        System.out.println(LocalDate.parse(strDate, formatter));
        System.out.println(formatter.format(LocalDate.parse(strDate, formatter)));

        System.out.printf("\n%s\n", localDate.plus(15, ChronoUnit.DAYS));
        System.out.println(localDate.plusDays(150));
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.getMonth());

        //LocalTime
        System.out.println("==== LocalTime ====");
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm:ss");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println(localTime.format(formatter2));
        System.out.println(localTime.format(formatter3));
        System.out.println(localTime.getHour());

        //LocalDateTime
        System.out.println("==== LocalDateTime ====");
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter4 = DateTimeFormatter.ISO_DATE_TIME;
        System.out.println(localDateTime.format(formatter4));
        System.out.println(localDateTime.toInstant(ZoneOffset.UTC));
        Date date = Date.from(localDateTime.toInstant(ZoneOffset.ofHours(+3)));
        System.out.println(date);

        Date date2 = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date2);
        ZoneId zoneId = calendar.getTimeZone().toZoneId();
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(calendar.toInstant(), zoneId);
        System.out.println(localDateTime2);

        System.out.println(Duration.between(localDateTime, localDateTime2).toMillis());
    }
}
