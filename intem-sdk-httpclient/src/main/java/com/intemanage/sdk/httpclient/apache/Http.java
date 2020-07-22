package com.intemanage.sdk.httpclient.apache;

import java.util.HashMap;

public class Http {

    public static class Response<T> {
        private int status;
        private T body;
    }

    public static void main(String[] args) {
        Request.post().url("")
                .headers(new Headers() {{
                    append("abc", "cbd");
                    append("bcd", "bcd");
                }})
                .urlParams(null)
                .formParams(null)
                .rawParams(null)
                .doReq();
    }

    public static class Request<T> {
        protected String url;
        protected Headers headers;
        public static PostRequest post() {
            return new PostRequest();
        }
        public static Request get() {
            return new PostRequest();
        }
        public T url(String url) {
            this.url = url;
            return (T) this;
        }
        public T headers(Headers headers) {
            this.headers = headers;
            return (T) this;
        }
        public Response doReq() {
            return new Response();
        }
    }
    public static class PostRequest extends Request<PostRequest> {
        private UrlParams urlParams;
        private FormParams formParams;
        private RawParams rawParams;
        public PostRequest() {
        }

        public PostRequest urlParams(UrlParams urlParams) {
            this.urlParams = urlParams;
            return this;
        }

        public PostRequest formParams(FormParams formParams) {
            this.formParams = formParams;
            return this;
        }

        public PostRequest rawParams(RawParams rawParams) {
            this.rawParams = rawParams;
            return this;
        }
    }

    public static class UrlParams extends HashMap<String, String> {
        public UrlParams append(String param, String value) {
            put(param, value);
            return this;
        }
    }

    /**
     * Body 表单传参数， key - value 形式
     */
    public static class FormParams extends HashMap<String, String> {
        public FormParams append(String param, String value) {
            put(param, value);
            return this;
        }
    }

    /**
     * Body raw传参
     */
    public static class RawParams {
        private String data;
        public RawParams append(String data) {
            return this;
        }
    }

    /**
     * Http Headers
     */
    public static class Headers extends HashMap<String, String> {
        public Headers append(String key, String value) {
            put(key, value);
            return this;
        }
        public Headers append(Header header, String value) {
            if (header != null) {
                put(header.key(), value);
            }
            return this;
        }
    }

    /**
     * Http request header
     */
    public enum ReqHeader implements Header {
        ;
        private String key;
        ReqHeader(String key) {
            this.key = key;
        }
        @Override
        public String key() {
            return key;
        }
    }

    /**
     * Http response header
     */
    public enum RespHeader implements Header {
        ;
        private String key;
        RespHeader(String key) {
            this.key = key;
        }
        @Override
        public String key() {
            return key;
        }
    }

    /**
     * Http header
     */
    public interface Header {
        String key();
    }
}
