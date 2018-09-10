package com.hly.dell;

import android.appwidget.AppWidgetProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * ~~~~~~文件描述:~~~~~~
 * ~~~~~~作者:huleiyang~~~~~~
 * ~~~~~~创建时间:2018/9/10~~~~~~
 * ~~~~~~更改时间:2018/9/10~~~~~~
 * ~~~~~~版本号:1~~~~~~
 */
public class BBombActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<String> list;
    private FloatingActionButton floating;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbomb_acitivity_layout);
        recyclerView = findViewById(R.id.recycler);
        floating = findViewById(R.id.floating);

        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("222");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myAdapter = new MyAdapter(list);
        recyclerView.setAdapter(myAdapter);

        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                floating.setVisibility(View.GONE);
                showPopWindow();
            }
        });
    }

    private void showPopWindow() {
        View contentView = LayoutInflater.from(BBombActivity.this).inflate(R.layout.pop_item_layout, null);
        popupWindow = new PopupWindow(contentView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.AnimationRight);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(floating,0,0); //下面
//        popupWindow.showAtLocation(floating, Gravity.RIGHT,0,0);
        contentView.findViewById(R.id.pop_item_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }
}
