package ru.health.assistance.data.network;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by sasha_merkulev on 27.03.2018.
 */


public class DateTimeTypeAdapter extends TypeAdapter<Date> {

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        out.value(getUTCDate(value));
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        Date date = null;
        try {
            if (in.peek().toString().equals("NULL")) {
                in.nextNull();
            } else {
                String timeStamp = in.nextString();
                SimpleDateFormat format = new SimpleDateFormat(FORMAT, Locale.getDefault());
                date = format.parse(timeStamp);
                int gmtOffset = TimeZone.getDefault().getRawOffset();
                date = new Date(date.getTime() + gmtOffset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    private static String getUTCDate(Date value){
        if (value == null) return "";
        int gmtOffset = TimeZone.getDefault().getRawOffset();
        SimpleDateFormat format = new SimpleDateFormat(FORMAT, Locale.getDefault());
        return format.format(new Date(value.getTime() - gmtOffset));
//        SimpleDateFormat format = new SimpleDateFormat(FORMAT);
//        return format.format(new Date(value.getTime()));
    }

    public static Date parse(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT, Locale.getDefault());
        return format.parse(date);
    }
}
