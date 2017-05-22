package com.juphoon.zeroflll.easydemo.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.juphoon.zeroflll.easydemo.R;

import static com.juphoon.zeroflll.easydemo.R.array.language;

public class WidgetActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    private Spinner xml_sp;
    private Spinner array_sp;
    private String[] items = new String[]{"Java", "JavaScrip", "PHP", "Html"};
    private AutoCompleteTextView auto_actv;

    private String[] cities = new String[]{"hangzhou", "ningbo", "wenzhou", "jinhua", "shaoxing",
            "jiaxing", "huzhou", "lishui", "taizhou", "zhoushan"};
    private MultiAutoCompleteTextView mautv_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);


        initView();
        initListener();

    }

    private void initView() {
        xml_sp = ((Spinner) findViewById(R.id.activity_widget_sp1));
        array_sp = ((Spinner) findViewById(R.id.activity_widget_sp2));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        array_sp.setAdapter(adapter);

        xml_sp.setSelection(0, false);
        array_sp.setSelection(0, false);


        auto_actv = ((AutoCompleteTextView) findViewById(R.id.activity_widget_actv));
        ArrayAdapter<String> autoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, cities);
        auto_actv.setAdapter(autoAdapter);

        mautv_tv = ((MultiAutoCompleteTextView) findViewById(R.id.activity_widget_mactv));
        mautv_tv.setAdapter(autoAdapter);

        //设定选项间隔使用逗号分隔。
        mautv_tv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private void initListener() {
        xml_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(WidgetActivity.this, getResources().getStringArray(language)[position] + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        array_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(WidgetActivity.this, items[position] + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        auto_actv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                AutoCompleteTextView atcv = (AutoCompleteTextView) v;
                if (hasFocus)
                    atcv.showDropDown();

            }
        });
    }




}
