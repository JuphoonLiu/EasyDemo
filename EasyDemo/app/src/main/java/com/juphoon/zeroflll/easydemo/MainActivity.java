package com.juphoon.zeroflll.easydemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juphoon.zeroflll.easydemo.okhttp.OkhttpActivity;
import com.juphoon.zeroflll.easydemo.retrofit.RetrofitActivity;
import com.juphoon.zeroflll.easydemo.rxjava.RxActivity;
import com.juphoon.zeroflll.easydemo.singtask.SingleTaskActivity;
import com.juphoon.zeroflll.easydemo.widget.WidgetActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView list_rv;
    private List<String> titleList;
    private ListAdapter mAdapter;
    private List<Class<? extends AppCompatActivity>> appList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_rv = ((RecyclerView) findViewById(R.id.activity_main_recycler_rv));
        list_rv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ListAdapter();
        list_rv.setAdapter(mAdapter);

        titleList = new ArrayList<>();
        appList = new ArrayList<>();
        addList("Retrofit", RetrofitActivity.class);
        addList("SingleTask", SingleTaskActivity.class);
        addList("OkHttp", OkhttpActivity.class);
        addList("RxJava", RxActivity.class);
        addList("Widget", WidgetActivity.class);

        Log.i("MANUFACTURE", Build.MANUFACTURER);
    }

    private void addList(String title, Class<? extends AppCompatActivity> app) {
        titleList.add(title);
        appList.add(app);
        mAdapter.notifyItemInserted(titleList.size() - 1);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, appList.get(list_rv.getChildAdapterPosition(v)));
        intent.putExtra("title", titleList.get(list_rv.getChildAdapterPosition(v)));
        startActivity(intent);
    }

    class ListAdapter extends RecyclerView.Adapter<ViewHolder>{
        private final int TYPE_ONE = 0;
        private final int TYPE_TWO = 1;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = new TextView(MainActivity.this);
            tv.setTextSize(20);
            tv.setPadding(0, 40, 0, 40);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setLayoutParams(lp);
            return new ViewHolder(tv);
        }

        @Override
        public int getItemViewType(int position) {
            if (position % 2 == 0) {
                return TYPE_TWO;
            }
            return TYPE_ONE;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.itemView.setBackgroundColor(getItemViewType(position) == TYPE_ONE ? Color.WHITE : Color.GRAY);
            ((TextView)holder.itemView).setTextColor(getItemViewType(position) == TYPE_ONE ? Color.BLACK : Color.BLUE);
            ((TextView) holder.itemView).setText(titleList.get(position));
            holder.itemView.setOnClickListener(MainActivity.this);
        }

        @Override
        public int getItemCount() {
            return titleList == null ? 0 : titleList.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
