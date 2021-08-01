package com.example.paintpentext.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paintpentext.R;
import com.example.paintpentext.view.MultiTouchView;

public class MultiTouchActivity extends AppCompatActivity {

    private static final String TAG = "MultiTouchActivity";

    private MultiTouchView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_touch);
        mView = findViewById(R.id.multi_touch_view);
    }

    public void clearMultiTouch(View view) {
        if (mView != null) {
            Log.i(TAG, "clearView");
            mView.clearCanvas();
        }
    }
}
