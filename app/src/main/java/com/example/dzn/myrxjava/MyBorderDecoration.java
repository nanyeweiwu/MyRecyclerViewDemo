package com.example.dzn.myrxjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by dzn on 2016/10/18.
 */
public class MyBorderDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private int mOritation;

    public MyBorderDecoration(Context context, int resId, int oritation) {
        mDivider = context.getResources().getDrawable(resId);
        this.mOritation = oritation;
        Log.i("ItemDecorationDivider", "ItemDecoration=" + oritation);
    }


    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (mOritation == LinearLayoutManager.VERTICAL) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight() - parent.getVerticalScrollbarWidth();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) parent.getChildAt(i)
                        .getLayoutParams();

                final int top = parent.getChildAt(i).getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        } else if (mOritation == LinearLayoutManager.HORIZONTAL) {
            final int top = parent.getPaddingTop();
//            final int bottom = parent.getHeight() -
//                    parent.getPaddingBottom();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) parent.getChildAt(i)
                        .getLayoutParams();
                final int left = parent.getChildAt(i).getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicHeight();

                final int bottom = parent.getChildAt(i).getBottom();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOritation == LinearLayoutManager.VERTICAL) {
//            outRect.set(0, 0, 0, mDivider.getIntrinsicWidth());
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            // outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else if (mOritation == LinearLayoutManager.HORIZONTAL) {
//            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }
    }
}
