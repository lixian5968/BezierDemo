package com.lx.bezierdemo.Demo3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by lixian on 2016/6/12.
 */
public class LxBierView extends View {
    public LxBierView(Context context) {
        super(context);
    }

    /**
     * 要画图形，最起码要有三个对象：
     * 1.颜色对象 Color
     * 2.画笔对象 Paint
     * 3.画布对象 Canvas
     */

    @Override
    public void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        //设置字体大小
        paint.setTextSize(100);

        //让画出的图形是空心的
        paint.setStyle(Paint.Style.STROKE);
        //设置画出的线的 粗细程度
        paint.setStrokeWidth(5);


        Path path = new Path();
        path.moveTo(500, 500);
        path.cubicTo(100, 150, 300, 0, 500, 150);
        canvas.drawPath(path, paint);




        Path path2 = new Path();
        path2.moveTo(500, 500);
        path2.cubicTo(900, 150, 700, 0, 500, 150);
        canvas.drawPath(path2, paint);

        super.onDraw(canvas);
    }
}
