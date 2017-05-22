package com.juphoon.zeroflll.easydemo.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.juphoon.zeroflll.easydemo.R;
import com.juphoon.zeroflll.easydemo.bean.Repo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RetrofitActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private String url = "http://120.26.85.195/api/topic/hot";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.26.85.195/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        RetrofitClient client = retrofit.create(RetrofitClient.class);
        Call<Repo> call = client.getData();
        call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                Toast.makeText(RetrofitActivity.this, response.body().getTips(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {

            }
        });
        
        /*RetrofitApi api = retrofit.create(RetrofitApi.class);
        Call<Repo> call = api.getForData("hot");
        call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
//                int data = response.body().getRESPONSE_STATUS();
                Log.i(TAG, "onResponse: " + response.body().getRESPONSE_STATUS());
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    interface RetrofitClient {
        @GET("topic/hot")
        Call<Repo> getData();
    }

    interface RetrofitApi {
        @GET("topic/{type}")
        Call<Repo> getForData(@Path("type") String type);
    }



}
