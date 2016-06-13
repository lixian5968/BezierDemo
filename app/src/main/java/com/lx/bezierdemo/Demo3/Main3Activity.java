package com.lx.bezierdemo.Demo3;

import android.app.Activity;
import android.os.Bundle;

public class Main3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new LxBierView(this));
    }

}
