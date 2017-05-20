package com.cyris.homeautomation.majorproject.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.cyris.homeautomation.majorproject.R;

import mehdi.sakout.fancybuttons.FancyButton;

public class FragmentSetVoltage extends Fragment {

    FragmentSetVoltageListener mCallBack;

    public interface FragmentSetVoltageListener
    {
        public void setVoltage(int voltage, int time);
    }

    public FragmentSetVoltage() {
        // Required empty public constructor
    }

    EditText etVoltage, etTime;
    FancyButton btnSetVoltage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_fragment_set_voltage, container, false);

        etVoltage=(EditText)v.findViewById(R.id.fragmentSettings_et_voltage);
        etTime=(EditText)v.findViewById(R.id.fragmentSettings_et_time);
        btnSetVoltage=(FancyButton)v.findViewById(R.id.fragmentSettings_btn_setVoltage);
        btnSetVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //perform action on clicking set voltage button
                checkForValidValueInEditTexts();

            }
        });

        return v;
    }

    public void checkForValidValueInEditTexts()
    {

        int voltage=Integer.parseInt(etVoltage.getText().toString());
        int time=Integer.parseInt(etTime.getText().toString());

        if(voltage>=0&&voltage<=300&&time>=0&&time<=2880)
        {
            //values appropriate
            sendValuesToMainActivity(voltage, time);
        }
        else
        {
            Toast.makeText(getActivity().getApplicationContext(), "Enter values in specified Range !", Toast.LENGTH_SHORT).show();
        }

    }

    public void sendValuesToMainActivity(int voltage, int time)
    {
        mCallBack.setVoltage(voltage, time);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallBack = (FragmentSetVoltageListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

}
