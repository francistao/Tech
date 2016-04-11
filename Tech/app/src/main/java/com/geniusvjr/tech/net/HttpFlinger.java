package com.geniusvjr.tech.net;

import android.os.AsyncTask;

import com.geniusvjr.tech.listeners.DataListener;
import com.geniusvjr.tech.net.parser.DefaultParser;
import com.geniusvjr.tech.net.parser.RespParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络执行引擎
 *
 * Created by dream on 16/4/9.
 */
public final class HttpFlinger {

    private static final DefaultParser DEFAULT_PARSER = new DefaultParser();

    public HttpFlinger() {
    }

    public static void get(String reqUrl,DataListener<String> listener)
    {

    }

    /**
     * 发送get请求
     *
     * @param reqUrl     网页地址
     * @param parser
     * @param listener    回调，执行在UI线程
     * @param <T>
     */
    public static <T> void get(final String reqUrl,final RespParser<T> parser,
                               final DataListener<T> listener)
    {
        new AsyncTask<Void, Void, T>()
        {

            @Override
            protected T doInBackground(Void... params) {
                HttpURLConnection urlConnection = null;
                try {
                    urlConnection = (HttpURLConnection) new URL(reqUrl).openConnection();
                    urlConnection.connect();
                    String result = streamToString(urlConnection.getInputStream());
                    return parser.parseResponse(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if(urlConnection != null)
                    {
                        urlConnection.disconnect();
                    }
                }
                // TODO : 当请求失败时数据会返回为null,导致很多地方需要判空,如何优化这一步呢?
                return null;
            }

            @Override
            protected void onPostExecute(T t) {
                if(listener != null)
                {
                    listener.onComplete(t);
                }
            }
        }.execute();
    }


    /**
     * 将流转换为字符串
     * @return
     */
    private static String streamToString(InputStream inputStream) throws IOException {
        StringBuilder sBuilder = new StringBuilder();
        String line = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = bufferedReader.readLine()) != null)
        {
            sBuilder.append(line).append("\n");
        }
        return sBuilder.toString();
    }


}
