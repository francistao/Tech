package com.geniusvjr.tech.presenter;

import com.geniusvjr.tech.mvpview.MvpView;

/**
 * Presenter抽象类
 *
 * Created by dream on 16/4/9.
 */
public abstract class BasePresenter<T extends MvpView> {
    protected T mView;

    public void attach(T view)
    {
        mView = view;
    }

    public void detach()
    {
        mView = null;
    }
}
