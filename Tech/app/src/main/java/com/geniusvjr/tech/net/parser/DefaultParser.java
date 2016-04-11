package com.geniusvjr.tech.net.parser;

import org.json.JSONException;

/**
 *
 * Created by dream on 16/4/9.
 */

public class DefaultParser implements RespParser<String>{

    @Override
    public String parseResponse(String result) throws JSONException {
        return result;
    }
}
