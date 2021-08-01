package com.example.paintpentext.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paintpentext.R;
import com.example.paintpentext.view.PaintPenView;

public class PaintPenActivity extends AppCompatActivity {

    private static final String TAG = "MultiTouchActivity";

    private PaintPenView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_pen);
        mView = findViewById(R.id.paint_pen_view);
    }

    public void clearPaintPen(View view) {
        if (mView != null) {
            Log.i(TAG, "clearView");
            mView.clearCanvas();
        }
    }
}
