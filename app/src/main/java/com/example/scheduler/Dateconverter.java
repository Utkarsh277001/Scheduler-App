package com.example.scheduler;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateconverter {

    public static String formatDate(Date date){

        SimpleDateFormat simpleDateFormat= (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("EEE , MMM D");
        return simpleDateFormat.format(date);

    }

    // code for hiding the key board;
    public static void hideSoftKeyboard(View view){
        InputMethodManager imm=(InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static int PriorityColor(Task task) {
        int color =0;
        if(task.priority==Priority.HIGH){
            color= Color.argb(100,201,21,23);
        }else if(task.priority==Priority.LOW){
            color=Color.argb(100,51,181,229);
        }else if(task.priority==Priority.MEDIUM){
            color=Color.argb(100,33,213,41);//(100,255,179,0);

        }
        return color;
    }
}
