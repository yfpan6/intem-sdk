package com.intemanage.sdk.httpclient;

/**
 * @Author panyunfeng04
 * @Date 2020-07-24
 */
public abstract class HttpClient {

    public static HttpClient defaultClient() {
        return null;
    }

    public abstract Http.Response get(Http.GetRequest getRequest);
    public abstract Http.Response post(String url, Http.BodyParams bodyParams);
    public abstract Http.Response put(String url, Http.BodyParams bodyParams);
    public abstract Http.Response delete(String url, Http.BodyParams bodyParams);
    public abstract Http.Response head(String url, Http.BodyParams bodyParams);
    public abstract Http.Response options(String url, Http.BodyParams bodyParams);
    public abstract Http.Response trace(String url, Http.BodyParams bodyParams);
    public abstract Http.Response connect(Http.ConnectRequest connectRequest);
}
