package com.example.paintpentext.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MultiPointView extends View {
    private static final String TAG = "MultiPointView";

    // 设置抗锯齿
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    /**
     * 历史圆形
     */
    private List<DrawCircle> mDrawMoveHistory = new ArrayList<>();

    public MultiPointView(Context context) {
        super(context);
        init();
    }

    public MultiPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MultiPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //描边
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
        if (mDrawMoveHistory == null || mDrawMoveHistory.isEmpty()) {
            return;
        }
        for (DrawCircle item : mDrawMoveHistory) {
            mPaint.setColor(item.drawColor);
            canvas.drawCircle(item.pointF.x, item.pointF.y, 50, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //多指触控需要使用 getActionMasked
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                Log.i(TAG, "MotionEvent.ACTION_DOWN");
                //重置所有 PointerId 为 -1
                clearTouchRecordStatus();
                //新增一个圆
                addNewCircle(event);
                //重绘
                invalidate();
                return true;
            }
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.i(TAG, "MotionEvent.ACTION_POINTER_UP");
                //屏幕上有一根指头抬起，但有别的指头未抬起时的事件
                int pointerId = event.getPointerId(event.getActionIndex());
                for (DrawCircle item : mDrawMoveHistory) {
                    if (item.pointerId == pointerId) {
                        //该手指已绘制结束，将此 PointerId 重置为 -1
                        item.pointerId = -1;
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.i(TAG, "MotionEvent.ACTION_POINTER_DOWN");
                //屏幕上已经有了手指，此时又有别的手指点击时事件
                addNewCircle(event);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "MotionEvent.ACTION_UP");
                //最后一根手指抬起，重置所有 PointerId
                clearTouchRecordStatus();
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG, "MotionEvent.ACTION_CANCEL");
                //事件被取消
                clearTouchRecordStatus();
                break;
            default:
                Log.i(TAG, "MotionEvent.default");
                break;
        }
        return true;
    }

    private void addNewCircle(MotionEvent event) {
        int pointerId = event.getPointerId(event.getActionIndex());
        float x = event.getX(event.findPointerIndex(pointerId));
        float y = event.getY(event.findPointerIndex(pointerId));
        PointF point = new PointF(x, y);
        DrawCircle drawCircle = new DrawCircle(pointerId, Color.RED, point);
        mDrawMoveHistory.add(drawCircle);
    }

    /**
     * 清除记录触摸事件的状态
     */
    private void clearTouchRecordStatus() {
        for (DrawCircle item : mDrawMoveHistory) {
            item.pointerId = -1;
        }
    }

    /**
     * 清空画布
     */
    public void clearCanvas() {
        mDrawMoveHistory.clear();
        invalidate();
    }


    /**
     * 绘制的圆对象
     */
    private static class DrawCircle {
        /**
         * 手指 ID，默认为 -1，手指离开后置位 -1
         */
        private int pointerId = -1;
        /**
         * 颜色
         */
        private int drawColor;
        /**
         * 圆心
         */
        private PointF pointF;

        DrawCircle(int pointerId, int drawColor, PointF point) {
            this.pointerId = pointerId;
            this.drawColor = drawColor;
            this.pointF = point;
        }

    }
}
