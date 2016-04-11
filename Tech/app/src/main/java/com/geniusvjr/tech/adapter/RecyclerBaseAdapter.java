package com.geniusvjr.tech.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geniusvjr.tech.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 适用于RecyclerView的抽象Adapter，封装了数据集、ViewHolder的创建与绑定过程，简化子类的操作
 *
 * @param <D>
 * @param <V>
 */
public abstract class RecyclerBaseAdapter<D, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V>{

    /**
     * RecyclerView中的数据集
     */
    protected final List<D> mDataSet = new ArrayList<D>();

    /**
     * 点击事件处理回调
     */
    private OnItemClickListener<D> mItemClickListener;

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    protected D getItem(int position)
    {
        return mDataSet.get(position);
    }

    public void addItems(List<D> items)
    {
        //移除已经存在的数据，避免数据重复
        items.removeAll(mDataSet);
        //添加新数据
        mDataSet.addAll(items);
        notifyDataSetChanged();
    }

    public void clear()
    {
        mDataSet.clear();
        notifyDataSetChanged();
    }


    /**
     * 绑定数据，主要分为两步，绑定数据与设置每项的点击事件处理
     *
     */
    @Override
    public void onBindViewHolder(V holder, int position) {
        final D item = getItem(position);
        bindDataToItemView(holder, item);
        setupItemViewClickListener(holder, item);
    }

    protected View inflateItemView(ViewGroup viewGroup, int layoutId)
    {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup,false);
    }

    public void setOnItemClickListener(OnItemClickListener<D> mItemClickListener)
    {
        this.mItemClickListener = mItemClickListener;
    }

    /**
     * ItemView的点击事件
     *
     * @param viewHolder
     * @param item
     */
    protected void setupItemViewClickListener(V viewHolder, final D item)
    {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener != null)
                {
                    mItemClickListener.onClick(item);
                }
            }
        });
    }
    /**
     * 将数据绑定在ItemView上
     */
    protected abstract void bindDataToItemView(V viewHolder,D item);
}
