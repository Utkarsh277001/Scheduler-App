package com.example.scheduler.Util;

import androidx.room.TypeConverter;
import com.example.scheduler.Priority;

import java.util.Date;


public class Converter {

    @TypeConverter
    public static Date fromTimestamp(Long value){
        return value == null ? null:new Date(value);
    }

    @TypeConverter
    public static Long datetoTs(Date date){
        return date == null ? null: date.getTime();
    }

    @TypeConverter
    public static String fromPriority (Priority priority){
        return priority ==null ? null:priority.name();
    }

    @TypeConverter
    public static Priority toPriority(String priority){
        return priority==null ? null:Priority.valueOf(priority);
    }
}
