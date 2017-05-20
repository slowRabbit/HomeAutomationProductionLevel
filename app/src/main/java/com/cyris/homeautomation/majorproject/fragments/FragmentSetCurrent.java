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


public class FragmentSetCurrent extends Fragment {

  FragmentSetCurrentListener mCallBack;

  public interface FragmentSetCurrentListener
  {
    public void setCurrent(int current, int time);
  }

  public FragmentSetCurrent()
  {

  }

  EditText etVoltage, etTime;
  FancyButton btnSetCurrent;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v=inflater.inflate(R.layout.fragment_fragment_set_current, container, false);

      etVoltage=(EditText)v.findViewById(R.id.fragmentSetCurrent_et_current);
      etTime=(EditText)v.findViewById(R.id.fragmentSetCurrent_et_time);
      btnSetCurrent=(FancyButton)v.findViewById(R.id.fragmentSetCurrent_btn_setVoltage);
      btnSetCurrent.setOnClickListener(new View.OnClickListener() {
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

    int current=Integer.parseInt(etVoltage.getText().toString());
    int time=Integer.parseInt(etTime.getText().toString());

    if(current>=0&&current<=1000&&time>=0&&time<=2880)
    {
      //values appropriate
      sendValuesToMainActivity(current, time);
    }
    else
    {
      Toast.makeText(getActivity().getApplicationContext(), "Enter values in specified Range !", Toast.LENGTH_SHORT).show();
    }

  }

  public void sendValuesToMainActivity(int current, int time)
  {
    mCallBack.setCurrent(current, time);

  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);

    try {
      mCallBack = (FragmentSetCurrentListener) activity;
    } catch (ClassCastException e) {
      throw new ClassCastException(activity.toString()
              + " must implement OnHeadlineSelectedListener");
    }

  }


}
