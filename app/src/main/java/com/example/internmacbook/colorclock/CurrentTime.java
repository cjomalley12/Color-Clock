package com.example.internmacbook.colorclock;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by internmacbook on 8/11/16.
 */
public class CurrentTime {
    private static final String TAG = "CurrentTime";

    private static Calendar c;
    private int seconds;
    private int minutes;
    private int hour;

    public CurrentTime(){
        getCurrentTime();
    }

    public String getCurrentTime() {
        String timeString = "";
        c = Calendar.getInstance();
        minutes = c.get(Calendar.MINUTE);

        hour = c.get(Calendar.HOUR_OF_DAY);
        seconds = c.get(Calendar.SECOND);

        Log.i(TAG, "Current hour: " + hour + "\nCurrent minutes: " + minutes);
        timeString += fixSmallValues(hour);
        timeString += fixSmallValues(minutes);
        timeString += fixSmallValues(seconds);

        return timeString;
    }

    public String formatTime(Date date) {
        DateFormat df = new SimpleDateFormat("h:mm a");
        return df.format(date);
    }

    public Date getDate () {
        return Calendar.getInstance().getTime();
    }

    private String fixSmallValues(int temp){
        String result = "";
        result += temp;
        if(temp < 10){
            return "0"  + result;
        }
        return result;
    }
}
