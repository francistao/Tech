package com.geniusvjr.tech.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.geniusvjr.tech.R;
import com.geniusvjr.tech.mvpview.ArticleDetailView;
import com.geniusvjr.tech.presenter.ArticleDetailPresenter;

/**
 *
 * 文章阅读页面，使用WebView加载文章
 *
 * Created by dream on 16/4/7.
 */
public class ArticleDetailActivity extends BaseActionBarActivity implements ArticleDetailView{

    ProgressBar mProgressBar;
    WebView mWebView;
    private String mPostId;
    private String mTitle;
    String mJobUrl;
    ArticleDetailPresenter mPresenter = new ArticleDetailPresenter();


    @Override
    protected int getContentViewResId() {
        return R.layout.activity_detail;
    }


    @Override
    protected void initWidgets() {
        mProgressBar = (ProgressBar) findViewById(R.id.loading_progressbar);
        mWebView = (WebView) findViewById(R.id.articles_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebSettings settings = mWebView.getSettings();
                settings.setBuiltInZoomControls(true);
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(newProgress);
                if(newProgress == 100)
                {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void afterOnCreate() {
        Bundle extraBundle = getIntent().getExtras();
        if(extraBundle != null && !extraBundle.containsKey("job_url"))
        {
            mPostId = extraBundle.getString("post_id");
            mTitle = extraBundle.getString("title") ;
        }
        else
        {
            mJobUrl = extraBundle.getString("job_url");
        }

        mPresenter.attach(this);

        // 从数据库上获取文章内容缓存，如果没有缓存则从网络获取
        if (!TextUtils.isEmpty(mPostId)) {
            mPresenter.fetchArticleContent(mPostId, mTitle);
        } else {
            mWebView.loadUrl(mJobUrl);
        }
    }

    @Override
    public void onFetchedArticleContent(String html) {
        mWebView.loadDataWithBaseURL("", html,
                "text/html", "utf8", "404");
    }

    @Override
    public void onShowLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }
}
