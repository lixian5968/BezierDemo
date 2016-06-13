package com.lx.bezierdemo.Demo2;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lx.bezierdemo.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class LxDemoActivity extends Activity {

    ImageView mFavorite;
    int width;
    int height;
    RelativeLayout all;

    Context ct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lx_demo);
        all = (RelativeLayout) findViewById(R.id.all);
        mFavorite = (ImageView) findViewById(R.id.mFavorite);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

        ct = this;

    }

    int riseDuration = 4000;
    int innerDelay = 200;
    int maxHeartNum = 8;
    int minHeartNum = 2;

    public void start(View v) {
        Observable.timer(innerDelay, TimeUnit.MILLISECONDS)
//                .repeat((int) (Math.random() * (maxHeartNum - minHeartNum)) + minHeartNum)
                .repeat(4)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {


                        ImageView imageView = new ImageView(ct);
                        imageView.setImageDrawable(ct.getResources().getDrawable(R.drawable.ic_favorite_light_blue_900_24dp));
                        all.addView(imageView);
                        imageView.setTranslationX(width/2);
                        imageView.setTranslationY(0);

                        ValueAnimator valueAnimator = getBesselAnimator(imageView, width, height);
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.play(valueAnimator);
                        animatorSet.start();
                    }
                });


    }

    private ValueAnimator getBesselAnimator(final ImageView mFavorite, int Width, int Height) {


        LxBezierPoint point1 = new LxBezierPoint(Width / 2, 0);

        int point2x = (int) (Width * 0.1 + Math.random() * 0.8 * Width);
        LxBezierPoint point2 = new LxBezierPoint(point2x, Height / 3);

        int point3x = (int) (Width * 0.1 + Math.random() * 0.8 * Width);
        LxBezierPoint point3 = new LxBezierPoint(point3x, Height / 3 * 2);

        LxBezierPoint point4 = new LxBezierPoint(Width / 2, Height - mFavorite.getHeight() * 2);


        LxBesselEvaluator mBesselEvaluator = new LxBesselEvaluator(point2, point3);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(mBesselEvaluator, point1, point4);
        valueAnimator.setDuration(riseDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LxBezierPoint newPoint = (LxBezierPoint) animation.getAnimatedValue();
                mFavorite.setTranslationX(newPoint.x);
                mFavorite.setTranslationY(newPoint.y);
                Log.e("lx", newPoint.x + "," + newPoint.y);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                all.removeView(mFavorite);
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
