package com.example.paintpentext.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paintpentext.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 跳转到画笔界面
    public void toPaintPenActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, PaintPenActivity.class);
        startActivity(intent);
    }

    // 跳转到多点触控画笔界面
    public void toMultiTouchActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, MultiTouchActivity.class);
        startActivity(intent);
    }

    // 跳转到多点触控界面
    public void toMultiPointActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, MultiPointActivity.class);
        startActivity(intent);
    }
}
