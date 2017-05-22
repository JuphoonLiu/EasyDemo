package com.juphoon.zeroflll.easydemo.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juphoon.zeroflll.easydemo.R;
import com.juphoon.zeroflll.easydemo.bean.Hot;
import com.juphoon.zeroflll.easydemo.bean.Repo;

import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {

    private ImageView img_iv;
    private Subscription mSubscription;
    private TextView title_tv;

    private String url = "http://www.laij.com.cn/Uploads/Attachment/2/766/04b47e94b64ed78aee822abe5949c62c.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        img_iv = ((ImageView) findViewById(R.id.activity_rx_img_iv));
        title_tv = ((TextView) findViewById(R.id.activity_rx_title_iv));

//        onRetrofit();
        onSimpleUse();
    }

    private void onSimpleUse() {
        
    }

    private void onRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("http://120.26.85.195/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mSubscription = retrofit.create(RxClient.class)
                .getReply()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Repo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        title_tv.setText("使用错误");
                    }

                    @Override
                    public void onNext(Repo repo) {
                        if (repo != null) {
                            List<Hot> hotList = repo.getRESPONSE_INFO().getList();
                            Random random = new Random();
                            int count = random.nextInt(10);
                            Hot hot = hotList.get(count - 1);
                            Glide.with(RxActivity.this)
                                    .load(hot.getUpfile())
                                    .into(img_iv);
                            title_tv.setText(hot.getTopic_id());
                        } else {
                            title_tv.setText("图片加载失败");
                        }
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription.isUnsubscribed() && mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    interface RxClient {
        @GET("topic/hot")
        Observable<Repo> getReply();
    }


}
