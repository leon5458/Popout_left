package com.hly.dell;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * ~~~~~~文件描述:~~~~~~
 * ~~~~~~作者:huleiyang~~~~~~
 * ~~~~~~创建时间:2018/9/10~~~~~~
 * ~~~~~~更改时间:2018/9/10~~~~~~
 * ~~~~~~版本号:1~~~~~~
 */
public class BombActivity extends AppCompatActivity{
    private Context context = null;
    private PopupWindow popupWindow;
    private int from = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.bomb_activity_layout);

        Button popLeftBtn = findViewById(R.id.left);
        Button popRightBtn = findViewById(R.id.right);

        popLeftBtn.setOnClickListener(popClick);
        popRightBtn.setOnClickListener(popClick);
    }
    View.OnClickListener popClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.left:{
                    from = Location.LEFT.ordinal();
                    break;
                }
                case R.id.right:{
                    from = Location.RIGHT.ordinal();
                    break;
                }
            }
            //调用此方法，menu不会顶置
            //popupWindow.showAsDropDown(v);
            initPopupWindow();

        }
    };
    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     */
    class popupDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }


    protected void initPopupWindow(){
        View popupWindowView = getLayoutInflater().inflate(R.layout.pop, null);
        //内容，高度，宽度
//        if(Location.BOTTOM.ordinal() == from){
//            popupWindow = new PopupWindow(popupWindowView, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
//        }else{
//            popupWindow = new PopupWindow(popupWindowView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.FILL_PARENT, true);
//        }
        //动画效果
        if(Location.LEFT.ordinal() == from){
            popupWindow = new PopupWindow(popupWindowView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.FILL_PARENT, true);
            popupWindow.setAnimationStyle(R.style.AnimationLeftFade);
        }else if(Location.RIGHT.ordinal() == from){
            popupWindow = new PopupWindow(popupWindowView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.FILL_PARENT, true);
            popupWindow.setAnimationStyle(R.style.AnimationRightFade);

        }

        //菜单背景色
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(dw);
        //宽度
        //popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
        //高度
        //popupWindow.setHeight(LayoutParams.FILL_PARENT);
        //显示位置
        if(Location.LEFT.ordinal() == from){
            popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_main, null), Gravity.LEFT, 0, 500);
        }else if(Location.RIGHT.ordinal() == from){
            popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_main, null), Gravity.RIGHT, 0, 500);
        }else if(Location.BOTTOM.ordinal() == from){
            popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_main, null), Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
        }
        //设置背景半透明
        backgroundAlpha(0.5f);
        //关闭事件
        popupWindow.setOnDismissListener(new popupDismissListener());

        popupWindowView.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
				/*if( popupWindow!=null && popupWindow.isShowing()){
					popupWindow.dismiss();
					popupWindow=null;
				}*/
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                return false;
            }
        });
        Button open = (Button)popupWindowView.findViewById(R.id.open);
        Button save = (Button)popupWindowView.findViewById(R.id.save);
        Button close = (Button)popupWindowView.findViewById(R.id.close);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Open", Toast.LENGTH_LONG).show();
                popupWindow.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Open", Toast.LENGTH_LONG).show();
                popupWindow.dismiss();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Open", Toast.LENGTH_LONG).show();
                popupWindow.dismiss();
            }
        });
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    /**
     * 菜单弹出方向
     *
     */
    public enum Location{
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }
}

