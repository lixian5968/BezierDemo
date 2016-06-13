package com.lx.bezierdemo.Demo4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lixian on 2016/6/12.
 */
public class Bezier4 extends View {
    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置

    private Paint mPaint;
    private int mCenterX, mCenterY;

    private PointF mCenter = new PointF(0, 0);
    private float mCircleRadius = 200;                  // 圆的半径
    private float mDifference = mCircleRadius * C;        // 圆形的控制点与数据点的差值


    private float mDuration = 1000;                     // 变化总时长
    private float mCurrent = 0;                         // 当前已进行时长
    private float mCount = 100;                         // 将时长总共划分多少份
    private float mPiece = mDuration / mCount;            // 每一份的时长


    public Bezier4(Context context) {
        this(context, null);

    }

    private PointF start1, end1, two1, three1;
    private PointF start2, end2, two2, three2;
    private PointF start3, end3, two3, three3;
    private PointF start4, end4, two4, three4;

    public Bezier4(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        start1 = new PointF(0, mCircleRadius);
        end1 = new PointF(mCircleRadius, 0);
        two1 = new PointF(mDifference, mCircleRadius);
        three1 = new PointF(mCircleRadius, mDifference);

        start2 = new PointF(0, mCircleRadius);
        end2 = new PointF(-mCircleRadius, 0);
        two2 = new PointF(-mDifference, mCircleRadius);
        three2 = new PointF(-mCircleRadius, mDifference);

        start3 = new PointF(0, -mCircleRadius);
        end3 = new PointF(-mCircleRadius, 0);
        two3 = new PointF(-mDifference, -mCircleRadius);
        three3 = new PointF(-mCircleRadius, -mDifference);

        start4 = new PointF(0, -mCircleRadius);
        end4 = new PointF(mCircleRadius, 0);
        two4 = new PointF(mDifference, -mCircleRadius);
        three4 = new PointF(mCircleRadius, -mDifference);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mCenterX, mCenterY); // 将坐标系移动到画布中央
        canvas.scale(1, -1);



        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(start1.x, start1.y);
        path.cubicTo(two1.x, two1.y, three1.x, three1.y, end1.x, end1.y);
        canvas.drawPath(path, mPaint);


        Path path2 = new Path();
        path2.moveTo(start2.x, start2.y);
        path2.cubicTo(two2.x, two2.y, three2.x, three2.y, end2.x, end2.y);
        canvas.drawPath(path2, mPaint);

        Path path3 = new Path();
        path3.moveTo(start3.x, start3.y);
        path3.cubicTo(two3.x, two3.y, three3.x, three3.y, end3.x, end3.y);
        canvas.drawPath(path3, mPaint);

        Path path4 = new Path();
        path4.moveTo(start4.x, start4.y);
        path4.cubicTo(two4.x, two4.y, three4.x, three4.y, end4.x, end4.y);
        canvas.drawPath(path4, mPaint);


        mCurrent += mPiece;
        if (mCurrent < mDuration){
            start1.y -= 120/mCount;
            start2.y -= 120/mCount;


            two3.y += 80/mCount;
            two4.y += 80/mCount;

            three3.y+=20/mCount;
            three4.y-=20/mCount;

            postInvalidateDelayed((long) mPiece);
        }

    }


}