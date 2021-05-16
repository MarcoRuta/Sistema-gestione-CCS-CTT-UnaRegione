package it.unisannio.ingegneriaDelSoftware.Util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateUtil {
    /**@param aDate  data da convertire in localDate*/
    public static LocalDate convertDateToLocalDate(Date aDate){
        assert aDate != null: "la data da convertire non puo essere null";
        LocalDate localDate = Instant.ofEpochMilli(aDate.getTime())
                .atZone(ZoneId.systemDefault()) //se non va, metti UTC
                .toLocalDate();
        return localDate;
    }

    /**@param localDate  data da convertire in Date*/
   public static Date convertLocalDateToDate(LocalDate localDate){
        assert localDate != null: "la data da convertire non puo essere null";
        Date date = Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.of("UTC"))
                .toInstant());
        return date;
    }

    /**@param date stringa da convertire in un oggetto localdate
     * @return un oggetto localdate istanziato a partire dal parametro passato alla funzione
     * @exception DateTimeParseException se la stringa passata come parametro non rispetta il Constants.DATEFORMAT
     * */
    public static LocalDate dateParser(String date) throws DateTimeParseException {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
    }
 

}
