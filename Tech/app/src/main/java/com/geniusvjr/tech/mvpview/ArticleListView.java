package com.geniusvjr.tech.mvpview;

import com.geniusvjr.tech.beans.Article;

import java.util.List;

/**
 * 展示文章列表的MVP View接口
 * Created by dream on 16/4/9.
 */
public interface ArticleListView extends MvpView{

    public void onFetchedArticles(List<Article> result);
    public void clearCacheArticles();
}
