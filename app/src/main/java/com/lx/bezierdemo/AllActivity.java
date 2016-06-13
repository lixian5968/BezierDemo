package com.lx.bezierdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lx.bezierdemo.Demo1.MainActivity;
import com.lx.bezierdemo.Demo2.LxDemoActivity;
import com.lx.bezierdemo.Demo3.Main3Activity;
import com.lx.bezierdemo.Demo4.Main4Activity;
import com.lx.bezierdemo.Demo5.Main5Activity;

public class AllActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
    }

    public void demo1(View v){
        Intent it = new Intent(AllActivity.this, MainActivity.class);
        startActivity(it);
    }

    public void demo2(View v){
        Intent it = new Intent(AllActivity.this, LxDemoActivity.class);
        startActivity(it);
    }


    public void demo3(View v){
        Intent it = new Intent(AllActivity.this, Main3Activity.class);
        startActivity(it);
    }

    public void demo4(View v){
        Intent it = new Intent(AllActivity.this, Main4Activity.class);
        startActivity(it);
    }

    public void demo5(View v){
        Intent it = new Intent(AllActivity.this, Main5Activity.class);
        startActivity(it);
    }

}
