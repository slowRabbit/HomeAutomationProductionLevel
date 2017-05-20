package com.cyris.homeautomation.majorproject.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyris.homeautomation.majorproject.R;

import mehdi.sakout.fancybuttons.FancyButton;


public class FragmentSettings extends Fragment implements View.OnClickListener{

    fragmentSettingsListener mCallback;


    public interface fragmentSettingsListener
    {
        public void connectToBluetoothDevice();
    }

    FancyButton btnConnect;

    public FragmentSettings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_settings, container, false);

        btnConnect=(FancyButton)v.findViewById(R.id.fragmentSettings_btn_connectBluetooth);
        btnConnect.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.fragmentSettings_btn_connectBluetooth:

                //do call interface Function in main activity to start bluetooth connection
                mCallback.connectToBluetoothDevice();

                break;
        }

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (fragmentSettingsListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }
}
