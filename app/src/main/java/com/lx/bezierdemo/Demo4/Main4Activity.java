package com.lx.bezierdemo.Demo4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lx.bezierdemo.R;

public class Main4Activity extends Activity {
    Intent it;
    Bundle bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        it = new Intent(this, BezierDemo.class);
        bd = new Bundle();

    }


    public void onclick1(View v) {
        bd.putInt("type", 1);
        it.putExtras(bd);
        startActivity(it);

    }

    public void onclick2(View v) {
        bd.putInt("type", 2);
        it.putExtras(bd);
        startActivity(it);

    }

    public void onclick3(View v) {
        bd.putInt("type", 3);
        it.putExtras(bd);
        startActivity(it);

    }


    public void onclick4(View v) {
        bd.putInt("type", 4);
        it.putExtras(bd);
        startActivity(it);

    }

}
