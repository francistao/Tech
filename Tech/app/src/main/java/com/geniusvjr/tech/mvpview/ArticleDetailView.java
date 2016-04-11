package com.geniusvjr.tech.mvpview;

/**
 * Created by dream on 16/4/10.
 */
public interface ArticleDetailView extends MvpView{
    public void onFetchedArticleContent(String html);
}
