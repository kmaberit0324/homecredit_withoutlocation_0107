package com.homecredit.takehomeexam;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SplashActivity extends AppCompatActivity {

    private ImageView ivLogo;

    enum BuildVariants{
        TEST,
        DEVELOPMENT,
        PRODUCTION
    }

    private BuildVariants BUILD = BuildVariants.PRODUCTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivLogo = findViewById(R.id.logo);

        Picasso.with(getApplicationContext()).load(getBuildIcon()).into(ivLogo);
    }

    private int getBuildIcon() {
        int icon = 0;
        if(BUILD == BuildVariants.PRODUCTION){
            icon = R.drawable.homecredit;
        } else if(BUILD == BuildVariants.TEST){
            icon = R.drawable.testing_icon;
        } else if(BUILD == BuildVariants.DEVELOPMENT){
            icon = R.drawable.develop_icon;
        }
        return icon;
    }

    @Override
    protected void onStart() {
        super.onStart();

        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
