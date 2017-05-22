package com.juphoon.zeroflll.easydemo.okhttp;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Zeroflll on 2017/5/7.
 */

public class OkHttpUtils {

    private static OkHttpUtils mInstance;

    private OkHttpClient.Builder builder;

    private OkHttpClient client;

    private Handler handler;


    public OkHttpUtils() {
        client = new OkHttpClient();
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        handler = new Handler(Looper.getMainLooper());
    }

    public synchronized static OkHttpUtils getInstance() {
        if (mInstance == null)
            mInstance = new OkHttpUtils();
        return mInstance;
    }

    public void getRequest(final String url, final HttpCallback httpCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                onRequest(url, httpCallback);
            }
        }).start();

    }


    private void onRequest(String url, final HttpCallback httpCallback) {

        Request request = new Request.Builder()
                .url(url)
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build();

        try {
            final Response response = client
                    .newCall(request)
                    .execute();
            if (response.isSuccessful()) {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpCallback.onSuccess(result);
                    }
                });
            } else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpCallback.onFailure();
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getRequest2(String url, final HttpCallback callback) {
        Request request = new Request.Builder()
                .url(url)
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onFailure();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String result = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSuccess(result);
                            }
                        });
                    }
                });
    }


    interface HttpCallback {
        void onSuccess(String response);

        void onFailure();
    }
}
