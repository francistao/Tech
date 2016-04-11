package com.geniusvjr.tech.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.geniusvjr.tech.beans.ArticleDetail;
import com.geniusvjr.tech.db.DatabaseHelper;
import com.geniusvjr.tech.listeners.DataListener;
import com.geniusvjr.tech.mvpview.ArticleDetailView;
import com.geniusvjr.tech.net.HtmlUtls;
import com.geniusvjr.tech.net.HttpFlinger;

/**
 * 文章详情页面的Presenter,负责加载文章内容。
 * 如果数据库中有缓存，那么使用缓存，
 * 否则从网络上下载内容到本地，并存储。
 * Created by dream on 16/4/10.
 */
public class ArticleDetailPresenter extends BasePresenter<ArticleDetailView>{

    /**
     * 加载文章的具体内容，先从数据库中加载，那么则不会从网络上获取
     *
     * @param postId
     * @param title
     */
    public void fetchArticleContent(final String postId,String title)
    {
        String articleContent = loadArticleContentFromDB(postId);
        if(!TextUtils.isEmpty(articleContent))
        {
            String htmlContent = HtmlUtls.wrapArticleContent(title, articleContent);
            mView.onFetchedArticleContent(htmlContent);
        }
        else
        {
            fetchContentFromServer(postId, title);
        }
    }

    public String loadArticleContentFromDB(String postId)
    {
        return DatabaseHelper.getInstance().loadArticleDetail(postId).content;
    }

    protected void fetchContentFromServer(final String postId,final String title)
    {
        mView.onShowLoading();
        String reqURL = "http://www.devtf.cn/api/v1/?type=article&post_id=" + postId;
        HttpFlinger.get(reqURL, new DataListener<String>() {
            @Override
            public void onComplete(String result) {
                mView.onHideLoading();
                if (TextUtils.isEmpty(result)) {
                    result = "未获取到文章内容~";
                }
                Log.e("-------->",result);
                mView.onFetchedArticleContent(HtmlUtls.wrapArticleContent(title, result));
                DatabaseHelper.getInstance().saveArticleDetail(new ArticleDetail(postId, result));
            }
        });
    }
}
