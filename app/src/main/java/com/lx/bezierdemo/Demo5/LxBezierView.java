package com.lx.bezierdemo.Demo5;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by lixian on 2016/6/13.
 */
public class LxBezierView extends RelativeLayout {


    Context ct;

    public LxBezierView(Context context) {
        super(context);
    }

    public LxBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ct = context;
    }

    public LxBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    List<Drawable> drawableList = new ArrayList<>();

    public void setDrawableList(List<Drawable> drawableList) {
        this.drawableList = drawableList;
    }


    int maxHeartNum = 8;
    int minHeartNum = 2;

    public void startAnimation(final int width, final int height) {
        Observable.timer(innerDelay, TimeUnit.MILLISECONDS)
                .repeat((int) (Math.random() * (maxHeartNum - minHeartNum)) + minHeartNum)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        startOnce(width, height);
                    }
                });


    }

    private float maxScale = 1.0f;
    private float minScale = 1.0f;

    private void startOnce(int width, int height) {
        ImageView imageView = new ImageView(ct);
        int heartDrawableIndex = (int) (drawableList.size() * Math.random());
        imageView.setImageDrawable(drawableList.get(heartDrawableIndex));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(viewWidth, viewHeight);

        addView(imageView,layoutParams);
        imageView.setTranslationX(width / 2);
        imageView.setTranslationY(0);

        ObjectAnimator riseAlphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.0f);
        riseAlphaAnimator.setDuration(riseDuration);
        ObjectAnimator riseScaleXAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", minScale, maxScale);
        riseScaleXAnimator.setDuration(riseDuration);
        ObjectAnimator riseScaleYAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", minScale, maxScale);
        riseScaleYAnimator.setDuration(riseDuration);

        ValueAnimator valueAnimator = getBesselAnimator(imageView, width, height);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(valueAnimator).with(riseAlphaAnimator).with(riseScaleXAnimator).with(riseScaleYAnimator);
        animatorSet.start();
    }

    int riseDuration = 4000;
    int innerDelay = 200;
    private int viewWidth = dp2pix(16), viewHeight = dp2pix(16);
    private int dp2pix(int dp){
        float scale = getResources().getDisplayMetrics().density;
        int pix = (int) (dp * scale + 0.5f);
        return pix;
    }
    private ValueAnimator getBesselAnimator(final ImageView mFavorite, int Width, int Height) {
        LxBezierPoint point1 = new LxBezierPoint(Width / 2, 0);
        int point2x = (int) (Width * 0.1 + Math.random() * 0.8 * Width);
        LxBezierPoint point2 = new LxBezierPoint(point2x, Height / 3);

        int point3x = (int) (Width * 0.1 + Math.random() * 0.8 * Width);
        LxBezierPoint point3 = new LxBezierPoint(point3x, Height / 3 * 2);

        LxBezierPoint point4 = new LxBezierPoint((int) (Math.random() * Width), Height - mFavorite.getHeight() * 2);

        LxBesselEvaluator mBesselEvaluator = new LxBesselEvaluator(point2, point3);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(mBesselEvaluator, point1, point4);
        valueAnimator.setDuration(riseDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LxBezierPoint newPoint = (LxBezierPoint) animation.getAnimatedValue();
                mFavorite.setTranslationX(newPoint.x);
                mFavorite.setTranslationY(newPoint.y);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(mFavorite);
                mFavorite.setImageDrawable(null);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return valueAnimator;
    }

    class LxBezierPoint {
        int x;
        int y;

        public LxBezierPoint() {
        }

        public LxBezierPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public class LxBesselEvaluator implements TypeEvaluator<LxBezierPoint> {
        LxBezierPoint point2;
        LxBezierPoint point3;


        public LxBesselEvaluator(LxBezierPoint point2, LxBezierPoint point3) {
            this.point2 = point2;
            this.point3 = point3;
        }

        public LxBezierPoint evaluate(float t, LxBezierPoint point1, LxBezierPoint point4) {
            LxBezierPoint point = new LxBezierPoint();
            point.x = (int) (point1.x * Math.pow((1 - t), 3) + 3 * point2.x * t * Math.pow((1 - t), 2) + 3 * point3.x * t * t * (1 - t) + point4.x * t * t * t);
            point.y = (int) (point1.y * Math.pow((1 - t), 3) + 3 * point2.y * t * Math.pow((1 - t), 2) + 3 * point3.y * t * t * (1 - t) + point4.y * t * t * t);
            return point;
        }

    }
}
