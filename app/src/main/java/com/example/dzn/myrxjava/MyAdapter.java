package com.example.dzn.myrxjava;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dzn on 2016/10/17.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MViewHolder> {
    private Context context;
    private List<User> listData;
    private MyItemListener myItemListener;

    public MyAdapter(List<User> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    public void setMyItemListener(MyItemListener myItemListener) {
        this.myItemListener = myItemListener;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void insertItem(User user, int position) {
        listData.add(position, user);
        this.notifyItemInserted(position);
//        this.notifyItemRangeChanged(position, getItemCount());
    }

    public void removeItem(int position) {
        listData.remove(position);
        Log.i("oisition=" + position, "geeCount=" + getItemCount());
        this.notifyItemRemoved(position);
//        this.notifyItemRangeChanged(position, getItemCount());
    }

    public void insertItems(ArrayList<User> userlist, int position) {
        listData.addAll(position, userlist);
        this.notifyItemRangeInserted(position, userlist.size());
    }

    public void addItemNormal(int position, User user) {
        listData.add(position, user);
        this.notifyDataSetChanged();
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cardview, parent, false);
        MViewHolder viewHolder = new MViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {
//        holder.pos = position;
        holder.mTextView.setText(listData.get(position).getUserName());
        holder.image.setImageResource(R.drawable.close_friend_icon);

    }

    public class MViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView image;
//        public int pos;

        public MViewHolder(View view) {
            super(view);
            this.mTextView = (TextView) view.findViewById(R.id.tv_friend_name);
            this.image = (ImageView) itemView.findViewById(R.id.img_friend_avatar);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (null != myItemListener)
                        myItemListener.subViewOnClick(view, getLayoutPosition());
                }
            });
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (null != myItemListener)
                        myItemListener.itemOnClick(view, getLayoutPosition());
//                        myItemListener.itemOnClick(view, pos);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (null != myItemListener)
                        myItemListener.itemOnLongClick(view, getLayoutPosition());
//                        myItemListener.itemOnLongClick(view, pos);
                    return true;
                }
            });
        }
    }
}
