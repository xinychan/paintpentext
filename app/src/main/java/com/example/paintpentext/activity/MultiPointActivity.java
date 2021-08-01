package com.example.paintpentext.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paintpentext.R;
import com.example.paintpentext.view.MultiPointView;


public class MultiPointActivity extends AppCompatActivity {
    private static final String TAG = "MultiPointActivity";

    private MultiPointView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_point);
        mView = findViewById(R.id.multi_point_view);
    }

    public void clearMultiPoint(View view) {
        if (mView != null) {
            Log.i(TAG, "clearView");
            mView.clearCanvas();
        }
    }
}
