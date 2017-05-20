package com.cyris.homeautomation.majorproject.extras;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.cyris.homeautomation.majorproject.activities.MainActivity;

public class SchedulerClass extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {
        // For our recurring task, we'll just display a message
        //Toast.makeText(arg0, "I have recieved something", Toast.LENGTH_SHORT).show();

       // MainActivity mainActivity=new MainActivity();
       // mainActivity.onSchedulerTaskAlarmRaised();

        Intent i = new Intent();
//        i.setClassName("com.cyris.homeautomation.majorproject", "com.cyris.homeautomation.majorproject.activities.MainActivity");
        i.setClass(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("fromScheduler", true);
        context.startActivity(i);

       // context.startActivity(new Intent(context, MainActivity.class));

    }

}
