package com.cyris.homeautomation.majorproject.fragments;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cyris.homeautomation.majorproject.R;
import com.cyris.homeautomation.majorproject.extras.SchedulerClass;


public class FragmentScheduler extends Fragment {


    public FragmentScheduler() {
        // Required empty public constructor
    }

    TextView tvConnectionStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_fragment_scheduler, container, false);


//        PendingIntent pendingIntent;
//        AlarmManager manager;
//
//        Intent alarmIntent = new Intent(getActivity(), SchedulerClass.class);
//        pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, alarmIntent, 0);
//
//        manager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
//        int interval = 5000;
//
//        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
//        Toast.makeText(getActivity(), "Alarm Set", Toast.LENGTH_SHORT).show();


        return v;
    }


}
