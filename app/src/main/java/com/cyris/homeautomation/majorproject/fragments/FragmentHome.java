package com.cyris.homeautomation.majorproject.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyris.homeautomation.majorproject.R;


public class FragmentHome extends Fragment {


    TextView tvLastActive;

    FragmentHomeSendValue mCallBack;

    public interface FragmentHomeSendValue
    {
        public void sendValueForTesting(int voltage, int time);
    }

    public FragmentHome() {
        // Required empty public constructor
    }

    TextView tvConnectionStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_home, container, false);

        tvConnectionStatus=(TextView)v.findViewById(R.id.tv_connectionStatus);
        tvLastActive=(TextView)v.findViewById(R.id.tvLastActive);


        tvLastActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("sending", "last active button clicked");
                sendValueToMainActivityFromHomeFragment(10);
            }
        });

        Bundle bundle=getArguments();
        if(bundle!=null)
        {
            String param=bundle.getString("params");
            String datetime=bundle.getString("datetime");
            updateViews(param, datetime);
        }

        return v;
    }

    public void sendValueToMainActivityFromHomeFragment(int val)
    {
        Log.i("sending", "value sent to main activity");
        mCallBack.sendValueForTesting(10, 20);
    }

    public void updateViews(String param, String time)
    {

        //tvLastUpdated.setText(time);
        String[] valueList=param.split("_");
       // tvVoltage.setText(valueList[0]);
       // tvCurrent.setText(valueList[1]);
       // tvTime.setText(valueList[2]);
       // tvTemperature.setText(valueList[3]);
       // tvHumidity.setText(valueList[4]);

    }

    public void updateConnectionStatus(String connectionStatus)
    {
        tvConnectionStatus.setText(connectionStatus);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallBack = (FragmentHomeSendValue) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

}
