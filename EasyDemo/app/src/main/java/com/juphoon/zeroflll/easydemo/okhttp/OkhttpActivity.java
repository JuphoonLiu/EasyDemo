package com.juphoon.zeroflll.easydemo.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.juphoon.zeroflll.easydemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class OkhttpActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private String url = "http://120.26.85.195/api/topic/hot";
    private ListAdapter mAdapter;
    private List<Hot> hotList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hotList = new ArrayList<>();

        recycler = ((RecyclerView) findViewById(R.id.activity_main_recycler_rv));
        mAdapter = new ListAdapter();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(mAdapter);

        /*OkHttpUtils.getInstance().getRequest(url, new OkHttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i("---->", response);

                JSONArray jsonArray = null;
                try {
                    JSONObject json = (JSONObject) new JSONTokener(response).nextValue();
                    json = json.getJSONObject("RESPONSE_INFO");
                    jsonArray = json.getJSONArray("list");
                    if (jsonArray == null) Log.i("---->", "null");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonArray != null)
                    hotList = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<Hot>>() {
                    }.getType());
                mAdapter.notifyItemInserted(0);
//                if (hotList == null)
//                    Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Log.i("---->", "response_1: 失败");
            }
        });*/

        OkHttpUtils.getInstance().getRequest2(url, new OkHttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String response) {
                JSONArray jsonArray = null;
                try {
                    JSONObject json = (JSONObject) new JSONTokener(response).nextValue();
                    json = json.getJSONObject("RESPONSE_INFO");
                    jsonArray = json.getJSONArray("list");
                    if (jsonArray == null) Log.i("---->", "null");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonArray != null)
                    hotList = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<Hot>>() {
                    }.getType());
                mAdapter.notifyItemInserted(0);
            }

            @Override
            public void onFailure() {
                Toast.makeText(OkhttpActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        });

        TextView h1 = new TextView(this);
        h1.setText("Header 1");
        mAdapter.addHeader(h1);
        TextView h2 = new TextView(this);
        h2.setText("Header 2");
        mAdapter.addHeader(h2);


        TextView f1 = new TextView(this);
        f1.setText("Footer 1");
        mAdapter.addFooter(h1);
    }


    private class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final int TYPE_LEFT = 0;
        private final int TYPE_RIGHT = 1;
        private final int TYPE_HEADER = -1;
        private final int TYPE_FOOTER = -1024;

        private SparseArray<View> headerList;
        private SparseArray<View> footerList;

        public ListAdapter() {
            headerList = new SparseArray<>();
            footerList = new SparseArray<>();
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (headerList.get(viewType) != null) {
                return new ViewHolder(headerList.get(viewType));
            } else if (footerList.get(viewType) != null) {
                return new ViewHolder(footerList.get(viewType));
            } else {

                LayoutInflater inflater = LayoutInflater.from(OkhttpActivity.this);

                switch (viewType) {
                    case TYPE_LEFT:
                        return new ViewLeft(inflater.inflate(R.layout.item_hot_left, parent, false));
                    case TYPE_RIGHT:
                        return new ViewRight(inflater.inflate(R.layout.item_hot_right, parent, false));

                    default:
                        return null;
                }
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (isHeader(position) || isFooter(position)) return;
            Hot hot = hotList.get(position - getHeaderCount());
            switch (getItemViewType(position)) {
                case TYPE_LEFT:
                    ViewLeft leftHolder = (ViewLeft) holder;
                    leftHolder.left_tv.setText(hot.getContent());
                    break;
                case TYPE_RIGHT:
                    ViewRight rightHolder = (ViewRight) holder;
                    rightHolder.right_tv.setText(hot.getContent());
                    break;
            }
        }

        public void addHeader(View view) {
            headerList.put(TYPE_HEADER + headerList.size(), view);
        }

        public void addFooter(View view) {
            footerList.put(TYPE_FOOTER + footerList.size(), view);
        }

        public int getHeaderCount() {
            return headerList == null ? 0 : headerList.size();
        }

        public int getFooterCount() {
            return footerList == null ? 0 : footerList.size();
        }

        public boolean isHeader(int position) {
            return position < getHeaderCount();
        }

        public boolean isFooter(int position) {
            return position >= getItemCount() - getFooterCount();
        }

        @Override
        public int getItemViewType(int position) {
            if (isHeader(position)) {
                return headerList.keyAt(position);
            } else if (isFooter(position)) {
//                return footerList.keyAt(position + getFooterCount() - getItemCount());
                return footerList.keyAt(position - getHeaderCount() - getRealCount());
            } else if (position % 3 == 0) {
                return TYPE_RIGHT;
            }
            return TYPE_LEFT;
        }

        private int getRealCount() {
            return hotList == null ? 0 : hotList.size();
        }

        @Override
        public int getItemCount() {
            return getHeaderCount() + getFooterCount() + getRealCount();
        }



        class ViewLeft extends RecyclerView.ViewHolder {

            private TextView left_tv;

            public ViewLeft(View itemView) {
                super(itemView);
                left_tv = (TextView) itemView.findViewById(R.id.content_left);
            }
        }

        class ViewRight extends RecyclerView.ViewHolder {
            private TextView right_tv;

            public ViewRight(View itemView) {
                super(itemView);
                right_tv = (TextView) itemView.findViewById(R.id.content_right);
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

    }
}
