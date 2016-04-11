package com.geniusvjr.tech.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geniusvjr.tech.R;
import com.geniusvjr.tech.beans.Article;


/**
 * 主页文章列表的Adapter
 * Created by dream on 16/4/6.
 */
public class ArticleAdapter extends RecyclerBaseAdapter<Article, ArticleAdapter.ArticleViewHolder> {


    @Override
    protected void bindDataToItemView(ArticleViewHolder viewHolder, Article item) {
        viewHolder.titleTv.setText(item.title);
        viewHolder.publishTimeTv.setText(item.publishTime);
        viewHolder.authorTv.setText(item.author);
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflateItemView(parent, R.layout.recyclerview_article_item);
        return new ArticleViewHolder(itemView);
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder
    {

        public TextView titleTv;
        public TextView publishTimeTv;
        public TextView authorTv;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.article_title_tv);
            publishTimeTv = (TextView) itemView.findViewById(R.id.article_time_tv);
            authorTv = (TextView) itemView.findViewById(R.id.article_author_tv);
        }
    }

    //    List<Article> mDataSet = new ArrayList<>();
//    OnItemClickListener<Article> mItemClickListener;
//
//    public ArticleAdapter(List<Article> dataSet) {
//        this.mDataSet = dataSet;
//    }
//
//    protected Article getItem(int position)
//    {
//        return mDataSet.get(position);
//    }
//
//
//    @Override
//    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return createArticleViewHolder(parent);
//    }
//
//    @Override
//    public void onBindViewHolder(ArticleViewHolder holder, int position) {
//        final Article item = getItem(position);
//        bindArticalToItemView(holder,item);
//        setupItemViewClickListener(holder, item);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener<Article> mItemClickListener)
//    {
//        this.mItemClickListener = mItemClickListener;
//    }
//
//    @Override
//    protected void bindDataToItemView(ArticleViewHolder viewHolder, Article item) {
//
//    }
//
//    protected void setupItemViewClickListener(ArticleViewHolder viewHolder,final Article item)
//    {
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mItemClickListener.onClick(item);
//            }
//        });
//    }
//
//    protected View inflateItemView(ViewGroup viewGroup,int layoutId,boolean attach)
//    {
//        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, attach);
//    }
//
//    //绑定文章数据
//    protected void bindArticalToItemView(ArticleViewHolder viewHolder, Article item)
//    {
//        viewHolder.titleTv.setText(item.title);
//        viewHolder.publishTimeTv.setText(item.publishTime);
//        viewHolder.authorTv.setText(item.author);
//    }
//    @Override
//    public int getItemCount() {
//        return mDataSet.size();
//    }
//
//    //创建文章ViewHolder
//    protected ArticleViewHolder createArticleViewHolder(ViewGroup viewGroup)
//    {
//        return new ArticleViewHolder(inflateItemView(viewGroup,R.layout.recyclerview_article_item, false));
//    }
    //文章的Viewholder

}
