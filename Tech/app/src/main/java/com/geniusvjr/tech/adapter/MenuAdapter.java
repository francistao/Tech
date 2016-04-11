package com.geniusvjr.tech.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geniusvjr.tech.R;
import com.geniusvjr.tech.beans.MenuItem;
import com.geniusvjr.tech.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dream on 16/4/6.
 */
public class MenuAdapter extends RecyclerBaseAdapter<MenuItem, MenuAdapter.MenuViewHolder> {


    @Override
    protected void bindDataToItemView(MenuViewHolder viewHolder, MenuItem item) {
        viewHolder.nameTextView.setText(item.text);
        viewHolder.userImageView.setImageResource(item.iconResId);
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuViewHolder(inflateItemView(parent, R.layout.menu_item));
    }

    //菜单项的ViewHolder
    public class MenuViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView userImageView;
        public TextView nameTextView;

        public MenuViewHolder(View itemView) {
            super(itemView);
            userImageView = (ImageView) itemView.findViewById(R.id.menu_icon_imageview);
            nameTextView = (TextView) itemView.findViewById(R.id.menu_text_tv);
        }
    }


    //    List<MenuItem> mDataSet = new ArrayList<MenuItem>();
//    OnItemClickListener<MenuItem> mItemClickListener;
//
//    public MenuAdapter(List<MenuItem> dataSet) {
//        this.mDataSet = dataSet;
//    }
//
//    @Override
//    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new MenuViewHolder(inflateItemView(parent, R.layout.menu_item));
//    }
//
//    //绑定数据
//    @Override
//    public void onBindViewHolder(MenuViewHolder viewHolder, int position) {
//        final MenuItem item = getItem(position);
//        viewHolder.nameTextView.setText(item.text);
//        viewHolder.userImageView.setImageResource(item.iconResId);
//        setOnItemClickListener(viewHolder, item);
//    }
//
//    //设置菜单项点击事件
//    public void setOnItemClickListener(OnItemClickListener<MenuItem> clickListener)
//    {
//        this.mItemClickListener = clickListener;
//    }
//
//    protected void setOnItemClickListener(MenuViewHolder viewHolder,final MenuItem item)
//    {
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mItemClickListener != null)
//                {
//                    mItemClickListener.onClick(item);
//                }
//            }
//        });
//    }
//
//    protected MenuItem getItem(int position)
//    {
//        return mDataSet.get(position);
//    }
//
//    protected View inflateItemView(ViewGroup viewGroup,int layoutId)
//    {
//        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mDataSet.size();
//    }
}
