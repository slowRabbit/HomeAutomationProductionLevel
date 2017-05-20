package com.cyris.homeautomation.majorproject.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.cyris.homeautomation.majorproject.R;


public class FragmentAppliances extends Fragment {


    public FragmentAppliances() {
    }

    TextView tvConnectionStatus;
    View v;
    Switch sw1Fan, sw2Light, sw3Cfl, sw4Tubelight, sw5Ac, sw6Geyser, sw7Motor, sw8Tv, sw9Bulb, sw10Power, sw11PowerSocket1, sw12PowerSocket2;
    String sw1State, sw2State,sw3State, sw4State, sw5State, sw6State, sw7State, sw8State, sw9State,sw10State, sw11State, sw12State;

    public static final String PreferenceName="AppliancesPreference";
    public static final String switch1PreferenceString="switch1";
    public static final String switch2PreferenceString="switch2";
    public static final String switch3PreferenceString="switch3";
    public static final String switch4PreferenceString="switch4";
    public static final String switch5PreferenceString="switch5";
    public static final String switch6PreferenceString="switch6";
    public static final String switch7PreferenceString="switch7";
    public static final String switch8PreferenceString="switch8";
    public static final String switch9PreferenceString="switch9";
    public static final String switch10PreferenceString="switch10";
    public static final String switch11PreferenceString="switch11";
    public static final String switch12PreferenceString="switch12";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_appliances, container, false);
        tvConnectionStatus=(TextView)v.findViewById(R.id.tv_connectionStatus);

        linkAllViews();
        intializeAllSwitchesFromSharedPreferences();

        return v;
    }

    public void linkAllViews()
    {
        sw1Fan=(Switch)v.findViewById(R.id.fragmentAppliances_sw_fan);
        sw2Light=(Switch)v.findViewById(R.id.fragmentAppliances_sw_Light);
        sw3Cfl=(Switch)v.findViewById(R.id.fragmentAppliances_sw_cfl);
        sw4Tubelight=(Switch)v.findViewById(R.id.fragmentAppliances_sw_tubelight);
        sw5Ac=(Switch)v.findViewById(R.id.fragmentAppliances_sw_ac);
        sw6Geyser=(Switch)v.findViewById(R.id.fragmentAppliances_sw_geyser);
        sw7Motor=(Switch)v.findViewById(R.id.fragmentAppliances_sw_motor);
        sw8Tv=(Switch)v.findViewById(R.id.fragmentAppliances_sw_TV);
        sw9Bulb=(Switch)v.findViewById(R.id.fragmentAppliances_sw_bulb);
        sw10Power=(Switch)v.findViewById(R.id.fragmentAppliances_sw_power);
        sw11PowerSocket1=(Switch)v.findViewById(R.id.fragmentAppliances_sw_powersocket1);
        sw12PowerSocket2=(Switch)v.findViewById(R.id.fragmentAppliances_sw_powersocket2);
    }

    public void intializeAllSwitchesFromSharedPreferences()
    {

        //getting values from shared preference file
        SharedPreferences prefs = getActivity().getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        sw1State=prefs.getString(switch1PreferenceString, "b");
        sw2State=prefs.getString(switch2PreferenceString, "d");
        sw3State=prefs.getString(switch3PreferenceString, "f");
        sw4State=prefs.getString(switch4PreferenceString, "h");
        sw5State=prefs.getString(switch5PreferenceString, "j");
        sw6State=prefs.getString(switch6PreferenceString, "l");
        sw7State=prefs.getString(switch7PreferenceString, "n");
        sw8State=prefs.getString(switch8PreferenceString, "p");
        sw9State=prefs.getString(switch9PreferenceString, "r");
        sw10State=prefs.getString(switch10PreferenceString, "t");
        sw11State=prefs.getString(switch11PreferenceString, "v");
        sw12State=prefs.getString(switch12PreferenceString, "x");
        //--

    }

    public void setAllSwitchesAccordingSharedPreferencesOnReading()
    {

        //for switch 1
        if(sw1State.contentEquals("a"))
           sw1Fan.setEnabled(true);
        else
            sw1Fan.setEnabled(false);
        //
        //for switch 1
        if(sw1State.contentEquals("c"))
            sw1Fan.setEnabled(true);
        else
            sw1Fan.setEnabled(false);
        //
        //for switch 1
        if(sw1State.contentEquals("e"))
            sw1Fan.setEnabled(true);
        else
            sw1Fan.setEnabled(false);
        //
        //for switch 1
        if(sw1State.contentEquals("g"))
            sw1Fan.setEnabled(true);
        else
            sw1Fan.setEnabled(false);
        //
        //for switch 1
        if(sw1State.contentEquals("i"))
            sw1Fan.setEnabled(true);
        else
            sw1Fan.setEnabled(false);
        //
        //for switch 1
        if(sw1State.contentEquals("k"))
            sw1Fan.setEnabled(true);
        else
            sw1Fan.setEnabled(false);
        //
        //for switch 1
        if(sw1State.contentEquals("m"))
            sw1Fan.setEnabled(true);
        else
            sw1Fan.setEnabled(false);
        //
        //for switch 1
        if(sw1State.contentEquals("o"))
            sw1Fan.setEnabled(true);
        else
            sw1Fan.setEnabled(false);
        //
        //for switch 1
        if(sw1State.contentEquals("q"))
            sw1Fan.setEnabled(true);
        else
            sw1Fan.setEnabled(false);
        //
        //for switch 1
        if(sw1State.contentEquals("s"))
            sw1Fan.setEnabled(true);
        else
            sw1Fan.setEnabled(false);
        //
        //for switch 1
        if(sw1State.contentEquals("u"))
            sw1Fan.setEnabled(true);
        else
            sw1Fan.setEnabled(false);
        //
        //for switch 1
        if(sw1State.contentEquals("w"))
            sw1Fan.setEnabled(true);
        else
            sw1Fan.setEnabled(false);
        //




    }

}
