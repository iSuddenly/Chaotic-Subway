package com.example.chaoticsubway.getInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetTime {
    static int day_num;
    static String time;

    public GetTime(){//인자값 필요 없음
        this.getDay();
        this.dateTime();
    }
    public static String getDay() {
        Calendar cal = Calendar.getInstance();

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        switch(dayOfWeek){
            case 6:
                day_num = 02;
                break;
            case 7:
                day_num = 03;
                break;
            default:
                day_num = 01;
        }
        System.out.println(day_num);
        return Integer.toString(day_num);
    }
    public static String dateTime() {
        Calendar cur_time = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
        time = dateFormat.format(cur_time.getTime());
        System.out.println(time);
        return time;
    }
}
