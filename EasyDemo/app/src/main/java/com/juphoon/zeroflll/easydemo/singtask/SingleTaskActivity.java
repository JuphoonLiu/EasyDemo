package com.juphoon.zeroflll.easydemo.singtask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.juphoon.zeroflll.easydemo.R;

public class SingleTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    
    private Button send_btn;
    private Button order_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        send_btn = ((Button) findViewById(R.id.activity_single_send_btn));
        order_btn = ((Button) findViewById(R.id.activity_single_order_btn));
        send_btn.setOnClickListener(this);
        order_btn.setOnClickListener(this);
        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_single_send_btn:
                Intent intent = new Intent(getString(R.string.single_task_filter));
                sendBroadcast(intent);
                break;
            case R.id.activity_single_order_btn:
                Intent orderBroadcast = new Intent(getString(R.string.order_broadcast));
                sendOrderedBroadcast(orderBroadcast, null);
                break;
        }

    }
}
