package com.github.larchaon.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.github.larchaon.android.util.TrialSuite;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logical(View view) {
        startActivity(new Intent(this, LogicalActivity.class));
    }

    public void graphics(View view) {
        startActivity(new Intent(this, GraphicsActivity.class));
    }
}
