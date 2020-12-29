package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;
import androidx.fragment.app.DialogFragment;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE="com.bignerdranch.android.criminalintent.date";

    private static final String ARG_TIME="time";

    private TimePicker mTimePicker;

    public static TimePickerFragment newInstance(Date date){
        Bundle args=new Bundle();
        args.putSerializable(ARG_TIME,date);

        TimePickerFragment fragment=new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Date date=(Date)getArguments().getSerializable(ARG_TIME);

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);

        View v= LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time,null);

        mTimePicker=(TimePicker)v.findViewById(R.id.dialog_time_picker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//判断时新的API还是旧的API
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        }
        else{
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minute);
        }
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int hour,minute;
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                                    hour = mTimePicker.getHour();
                                    minute=mTimePicker.getMinute();
                                }
                                else {
                                    hour=mTimePicker.getCurrentHour();
                                    minute=mTimePicker.getCurrentMinute();
                                }
                                Date date=new GregorianCalendar(year,month,day,hour,minute).getTime();
                                sendResult(Activity.RESULT_OK,date);
                            }
                        })
                .create();
    }
    public void sendResult(int resultCode,Date date){
        if (getTargetFragment()==null){
            return;
        }
        Intent intent =new Intent();
        intent.putExtra(EXTRA_DATE,date);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(),resultCode,intent);
    }

}
