package com.geniusvjr.tech.beans;

/**
 * Created by dream on 16/4/6.
 */
public class Article {

    public String title;
    public String publishTime;
    public String author;
    public String post_id;
    public int category;

    public Article() {
    }

    public Article(String pid) {
        post_id = pid;
    }

    @Override
    public String toString() {
        return "Article [title=" + title + ", publishTime=" + publishTime
                + ", author=" + author + ", post_id=" + post_id + ", category="
                + category + "]";
    }
}
