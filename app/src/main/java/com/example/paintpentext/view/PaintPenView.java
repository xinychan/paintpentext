package com.example.paintpentext.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 画笔自定义View
 */
public class PaintPenView extends View {

    private static final String TAG = "PaintPenView";

    // 上一次的触摸点x坐标
    private float preX = 0.0f;

    // 上一次触摸点y坐标
    private float preY = 0.0f;

    private Path mPath = new Path();

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    public PaintPenView(Context context) {
        super(context);
        init();
    }

    public PaintPenView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintPenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //画笔为描边
        mPaint.setStyle(Paint.Style.STROKE);
        //颜色
        mPaint.setColor(Color.RED);
        //笔触为圆形
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //画笔大小
        mPaint.setStrokeWidth(10f);
        //View的背景颜色
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画线
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "MotionEvent.ACTION_DOWN");
                //手指按下的时候
                //将起始点移动到当前坐标
                mPath.moveTo(event.getX(), event.getY());
                //记录上次触摸的坐标，注意ACTION_DOWN方法只会执行一次
                preX = event.getX();
                preY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "MotionEvent.ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "MotionEvent.ACTION_MOVE");
                //手指移动的时候
                //绘制圆滑曲线，即贝塞尔曲线,贝塞尔曲线这个知识自行了解
                mPath.quadTo(preX, preY, event.getX(), event.getY());
                preX = event.getX();
                preY = event.getY();
                break;
            default:
                Log.i(TAG, "MotionEvent.default");
                break;
        }
        // 重新绘制，会调用onDraw方法
        invalidate();
        return true;
    }

    /**
     * 清空画布
     */
    public void clearCanvas() {
        mPath.reset();
        invalidate();
    }
}
