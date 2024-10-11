package client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) throws ParseException {
        String beginDateToFind = "1970-01-01 00:00:00";
        String endDateToFind = "2200-12-31 23:59:59";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date begindate, enddate;

        begindate = dateFormat.parse(beginDateToFind);
        enddate = dateFormat.parse(endDateToFind);
        System.out.println(begindate);
        System.out.println(enddate);
        if(begindate.before(enddate)){
            System.out.println("add");
        }
    }
}

