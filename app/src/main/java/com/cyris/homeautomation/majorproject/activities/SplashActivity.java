package com.cyris.homeautomation.majorproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyris.homeautomation.majorproject.R;

public class SplashActivity extends Activity {

    TextView tvTitle, tvSubTitle;
    LinearLayout lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        tvTitle=(TextView)findViewById(R.id.tvSplashTitle);
        tvSubTitle=(TextView)findViewById(R.id.tvSplashSubTitle);
        lvMain=(LinearLayout)findViewById(R.id.lvSplashActivity);

        lvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent();
                i.setClass(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });

        //tvTitle.setTypeface(EasyFonts.caviarDreams(this));

        //tvSubTitle.setTypeface(EasyFonts.caviarDreamsBold(this));

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                Intent i=new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(i);
//            }
//        }, 2000);

    }


}
