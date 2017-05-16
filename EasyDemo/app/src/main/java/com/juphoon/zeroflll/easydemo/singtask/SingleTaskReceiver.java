package com.juphoon.zeroflll.easydemo.singtask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Zeroflll on 2017/5/10.
 */

public class SingleTaskReceiver extends BroadcastReceiver {
    private final String TAG = getClass().getSimpleName();
    
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive");
        Intent i = new Intent(context, SingleTaskActivity.class);
        context.startActivity(i);
    }
}
