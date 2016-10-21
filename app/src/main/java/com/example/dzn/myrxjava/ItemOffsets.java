package com.example.dzn.myrxjava;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dzn on 2016/10/19.
 */
public class ItemOffsets extends RecyclerView.ItemDecoration {
    private int spase;

    public ItemOffsets(int height) {
        spase = height;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = spase;
    }
}
