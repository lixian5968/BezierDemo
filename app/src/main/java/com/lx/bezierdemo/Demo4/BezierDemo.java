package com.lx.bezierdemo.Demo4;

import android.app.Activity;
import android.os.Bundle;

public class BezierDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra("type", 1);
        if (type == 1) {
            setContentView(new Bezier(this));
        }
        if (type == 2) {
            setContentView(new Bezier2(this));
        }
        if (type == 3) {
            setContentView(new Bezier3(this));
        }
        if (type == 4) {
            setContentView(new Bezier4(this));
        }
    }


}
