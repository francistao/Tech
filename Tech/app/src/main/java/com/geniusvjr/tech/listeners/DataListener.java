package com.geniusvjr.tech.listeners;

/**
 * Created by dream on 16/4/9.
 */
public interface DataListener<T> {
    void onComplete(T result);
}
