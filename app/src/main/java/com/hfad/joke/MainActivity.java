package com.hfad.joke;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStartService(View view) {
        //1. START SERVICE BY PASSING IT AN INTENT
        Intent intent = new Intent(this, DelayedMessageService.class);
        intent.putExtra(DelayedMessageService.MESSAGE, "Post-apocalyptic understandings lead to the beauty.");
        startService(intent);
    }
}
