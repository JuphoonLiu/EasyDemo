package com.juphoon.zeroflll.easydemo.singtask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Zeroflll on 2017/5/15.
 */

public class OrderThirdBroadcastReceiver extends BroadcastReceiver {
    private final String TAG = "OrderBroadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: Third");
//        abortBroadcast();
    }
}
