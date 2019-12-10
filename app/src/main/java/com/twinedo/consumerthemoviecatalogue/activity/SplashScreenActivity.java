package com.twinedo.consumerthemoviecatalogue.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.twinedo.consumerthemoviecatalogue.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Animation splashAnimation = AnimationUtils.loadAnimation(this, R.anim.app_splash);

        ImageView logo = findViewById(R.id.app_logo);
        logo.startAnimation(splashAnimation);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent gotoHome = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(gotoHome);
                finish();
            }
        }, 2000);
    }
}
