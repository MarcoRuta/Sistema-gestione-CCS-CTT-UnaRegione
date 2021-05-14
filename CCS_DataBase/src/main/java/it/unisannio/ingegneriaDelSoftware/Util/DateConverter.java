package it.unisannio.ingegneriaDelSoftware.Util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {
    /**
     * Metodo che permette di passare da una Date ad una LocalDate
     * @param aDate  data da convertire in localDate
     * @return aLocalDate
     * 
     * */
    public static LocalDate convertDateToLocalDate(Date aDate){
        assert aDate != null: "la data da convertire non puo essere null";
        LocalDate localDate = Instant.ofEpochMilli(aDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate;
    }

    /**
     * Metodo che permette di passare da una LocalDate ad una Date
     * @param aLocalDate  localDate da convertire in Date
     * @return aDate
     * 
     * */
    public static Date convertLocalDateToDate(LocalDate localDate){
        assert localDate != null: "la data da convertire non puo essere null";
        Date date = Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        return date;
    }
}
