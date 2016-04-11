package com.geniusvjr.tech.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geniusvjr.tech.R;
import com.geniusvjr.tech.activity.ArticleDetailActivity;
import com.geniusvjr.tech.adapter.ArticleAdapter;
import com.geniusvjr.tech.beans.Article;
import com.geniusvjr.tech.listeners.OnItemClickListener;
import com.geniusvjr.tech.mvpview.ArticleListView;
import com.geniusvjr.tech.presenter.ArticleListPresenter;
import com.geniusvjr.tech.widgets.AutoLoadRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章列表主界面，包含自动滚动广告栏、文章列表
 * Created by dream on 16/4/6.
 */
public class ArticleListFragment extends Fragment implements
        SwipeRefreshLayout.OnRefreshListener, AutoLoadRecyclerView.OnLoadListener, ArticleListView{

    //文章Adapter
    protected ArticleAdapter mAdapter;
    //下拉刷新组件
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    //文章RecyclerView
    protected AutoLoadRecyclerView mRecyclerView;

    private ArticleListPresenter mPresenter = new ArticleListPresenter();
    //文章列表
    final protected List<Article> mDataSet = new ArrayList<>();
    //文章的页面索引，用于分页加载
    private int mPageIndex = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        initRefreshView(rootView);
        initAdapter();
        mSwipeRefreshLayout.setRefreshing(true);
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.attach(this);
        mPresenter.fetchLastestArticles();
    }

    protected void initRefreshView(View rootView)
    {
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (AutoLoadRecyclerView) rootView.findViewById(R.id.articles_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setOnLoadListener(this);
    }

    protected void initAdapter()
    {
        mAdapter = new ArticleAdapter();
        mAdapter.setOnItemClickListener(new OnItemClickListener<Article>() {
            @Override
            public void onClick(Article item) {
                if (item != null) {
                    //调整到详情页面查看文章内容
                    jumpToDetailActivity(item);
                }
            }
        });
        //设置Adapter
        mRecyclerView.setAdapter(mAdapter);
    }

    private void jumpToDetailActivity(Article article) {
        Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
        intent.putExtra("post_id", article.post_id);
        intent.putExtra("title", article.title);
        startActivity(intent);
    }

    private void loadArticle(Article article) {
        Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
        intent.putExtra("post_id", article.post_id);
        intent.putExtra("title", article.title);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mPresenter.fetchLastestArticles();
    }

    @Override
    public void onLoad() {
        mPresenter.loadNextPageArticles();
    }

    @Override
    public void onFetchedArticles(List<Article> result) {
        mAdapter.addItems(result);
    }

    @Override
    public void clearCacheArticles() {
        mAdapter.clear();
    }

    @Override
    public void onShowLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onHideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }
}
