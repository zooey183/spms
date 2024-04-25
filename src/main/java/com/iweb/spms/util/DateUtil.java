package com.iweb.spms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String dateToString(Date date){
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }
}
