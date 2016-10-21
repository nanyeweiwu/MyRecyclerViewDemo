package com.example.dzn.myrxjava;

import android.view.View;

/**
 * Created by dzn on 2016/10/17.
 */
public interface MyItemListener {
    //item点击事件
    public void itemOnClick(View view, int position);

    //item子view点击事件
    public void subViewOnClick(View view, int position);

    //item长按事件
    public void itemOnLongClick(View view, int position);
}
