package com.geniusvjr.tech.net.parser;

import org.json.JSONException;

/**
 * Created by dream on 16/4/9.
 */
public interface RespParser<T> {

    public T parseResponse(String result) throws JSONException;
}
