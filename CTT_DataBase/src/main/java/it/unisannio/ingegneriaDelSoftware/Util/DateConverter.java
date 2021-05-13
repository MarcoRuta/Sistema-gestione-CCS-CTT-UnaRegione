package it.unisannio.ingegneriaDelSoftware.Util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {
    /**@param aDate  data da convertire in localDate*/
    public static LocalDate convertDateToLocalDate(Date aDate){
        assert aDate != null: "la data da convertire non puo essere null";
        LocalDate localDate = Instant.ofEpochMilli(aDate.getTime())
                .atZone(ZoneId.systemDefault()) //se non va, metti UTC
                .toLocalDate();
        return localDate;
    }

    /**@param aDate  data da convertire in Date*/
   public static Date convertLocalDateToDate(LocalDate localDate){
        assert localDate != null: "la data da convertire non puo essere null";
        Date date = Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.of("UTC"))
                .toInstant());
        return date;
    }
 

}
