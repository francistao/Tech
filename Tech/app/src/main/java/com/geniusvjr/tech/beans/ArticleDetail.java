package com.geniusvjr.tech.beans;

/**
 * Created by dream on 16/4/9.
 */
public class ArticleDetail {

    public String postId;
    public String content;

    public ArticleDetail()
    {

    }

    public ArticleDetail(String pid,String html)
    {
        postId = pid;
        content = html;
    }
}
