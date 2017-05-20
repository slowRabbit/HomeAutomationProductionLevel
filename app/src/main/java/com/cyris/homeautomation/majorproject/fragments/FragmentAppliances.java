package com.cyris.homeautomation.majorproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyris.homeautomation.majorproject.R;


public class FragmentAppliances extends Fragment {


    public FragmentAppliances() {
        // Required empty public constructor
    }

    TextView tvConnectionStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_appliances, container, false);

        tvConnectionStatus=(TextView)v.findViewById(R.id.tv_connectionStatus);

        Bundle bundle=getArguments();
        if(bundle!=null)
        {
            String param=bundle.getString("params");
            String datetime=bundle.getString("datetime");
            updateViews(param, datetime);
        }

        return v;
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

}
