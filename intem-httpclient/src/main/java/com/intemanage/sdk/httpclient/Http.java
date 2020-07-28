package com.intemanage.sdk.httpclient;

import com.intemanage.sdk.utils.Asserts;
import com.intemanage.sdk.utils.Strings;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 本工具只解决最简单的请求响应问题。
 * @author panyunfeng
 */
public class Http {

    public static class Response<T> {
        private int status;
        private T body;
    }

    public static abstract class Client {
        private static Client defaultClient;

        public static Client defaultClient() {
            return defaultClient;
        }

        public static void registerDefaultClient(Client client) {
            Client.defaultClient = client;
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

    /**
     * GET：向特定的资源发出请求。
     * @return
     */
    public static GetRequest doGet(String url) {
        return new GetRequest(url);
    }
    public static GetRequest doGet(String url, Client client) {
        return new GetRequest(url, client);
    }

    /**
     * POST：向指定资源提交数据进行处理请求（例如提交表单或者上传文件）。数据被包含在请求体中。POST请求可能会导致新的资源的创建和/或已有资源的修改。
     * @param url
     * @return
     */
    public static PostRequest doPost(String url) {
        return new PostRequest(url);
    }
    public static PostRequest doPost(String url, Client client) {
        return new PostRequest(url, client);
    }

    /**
     * PUT：向指定资源位置上传其最新内容。
     * @param url
     * @return
     */
    public static PutRequest doPut(String url) {
        return new PutRequest(url);
    }
    public static PutRequest doPut(String url, Client client) {
        return new PutRequest(url, client);
    }

    /**
     * DELETE：请求服务器删除 Request-URI 所标识的资源。
     * @param url
     * @return
     */
    public static DeleteRequest doDelete(String url) {
        return new DeleteRequest(url);
    }
    public static DeleteRequest doDelete(String url, Client client) {
        return new DeleteRequest(url, client);
    }

    /**
     * HEAD：向服务器索要与GET请求相一致的响应，只不过响应体将不会被返回。这一方法可以在不必传输整个响应内容的情况下，就可以获取包含在响应消息头中的元信息。
     * @param url
     * @return
     */
    public static HeadRequest doHead(String url) {
        return new HeadRequest(url);
    }
    public static HeadRequest doHead(String url, Client client) {
        return new HeadRequest(url, client);
    }

    /**
     * OPTIONS：返回服务器针对特定资源所支持的HTTP请求方法。也可以利用向Web服务器发送'*'的请求来测试服务器的功能性。
     * @param url
     * @return
     */
    public static OptionsRequest doOptions(String url) {
        return new OptionsRequest(url);
    }
    public static OptionsRequest doOptions(String url, Client client) {
        return new OptionsRequest(url, client);
    }

    /**
     * TRACE：回显服务器收到的请求，主要用于测试或诊断。
     * @param url
     * @return
     */
    public static TraceRequest doTrace(String url) {
        return new TraceRequest(url);
    }
    public static TraceRequest doTrace(String url, Client client) {
        return new TraceRequest(url, client);
    }

    /**
     * CONNECT：HTTP/1.1 协议中预留给能够将连接改为管道方式的代理服务器。
     * @param url
     * @return
     */
    public static ConnectRequest doConnect(String url) {
        return new ConnectRequest(url);
    }
    public static ConnectRequest doConnect(String url, Client client) {
        return new ConnectRequest(url, client);
    }

    public enum Protocol {
        HTTP("http"), HTTPS("https");
        private String text;
        Protocol(String text) {
            this.text = text;
        }
        public String text() {
            return text;
        }

        public static Protocol of(String text, Protocol def) {
            if (HTTP.text.equalsIgnoreCase(text)) {
                return HTTP;
            }
            if (HTTPS.text.equalsIgnoreCase(text)) {
                return HTTPS;
            }
            return def;
        }
    }

    public static class Url {
        /**
         * url = (http:// | https://) + hostname + : + port + path + ? + urlEncoded(params)
         */
        private StringBuilder url;
        /**
         * 参数计数器
         */
        private int paramCounter;
        /**
         * 锚 # + urlEncoded(fragment)
         */
        private String fragment;

        public Url() {
            this.url = new StringBuilder();
        }

        public Url(String url) {
            this.url = new StringBuilder(url);
        }

        public static Url of(String url) {
            return new Url(url);
        }

        public static Url of(String protocol, String host, String path) {
            return of(Protocol.of(protocol, Protocol.HTTP), host, -1, path);
        }

        public static Url of(Protocol protocol, String host, String path) {
            return of(protocol, host, -1, path);
        }

        public static Url of(String protocol, String host, int port, String path) {
            return of(Protocol.of(protocol, Protocol.HTTP), host, port, path);
        }

        public static Url of(Protocol protocol, String hostname, int port, String path) {
            Asserts.isNotNull(protocol, "http protocol is null.");
            Asserts.isFalse(Strings.isBlank(hostname), "htt hostname is null");

            Url url = new Url();
            url.url.append(protocol.text()).append("://").append(hostname);

            if (port > 0) {
                url.url.append(":").append(port);
            }

            if (Strings.isBlank(path)) {
                return url;
            }

            if (url.url.lastIndexOf(Strings.SLASH) != -1) {
                if (path.startsWith(Strings.SLASH)) {
                    url.url.subSequence(0, url.url.length() - 1);
                }
                url.url.append(path);
            } else {
                if (!path.startsWith(Strings.SLASH)) {
                    url.url.append(Strings.SLASH);
                }
                url.url.append(path);
            }

            return url;
        }

        public Url addParam(String key, String value) {
            if (Strings.isBlank(key)) {
                return this;
            }

            if (paramCounter == 0) {
                url.append(Strings.QUESTION);
            }

            url.append(key);

            if (Strings.isNotBlank(value)) {
                try {
                    url.append(Strings.EQUAL).append(URLEncoder.encode(value, Strings.CHARSET_UTF_8));
                } catch (UnsupportedEncodingException e) {
                    throw new IllegalArgumentException("http url param value invalid: " + value, e);
                }
            }

            return this;
        }

        @Override
        public String toString() {
            if (Strings.isNotBlank(fragment) ) {
                try {
                    url.append(Strings.SHARP).append(URLEncoder.encode(fragment, Strings.CHARSET_UTF_8));
                } catch (UnsupportedEncodingException e) {
                    throw new IllegalArgumentException("http url fragment value invalid: " + fragment, e);
                }
            }
            return url.toString();
        }
    }

    public static class Request<T> {
        protected String url;
        protected Client client;
        protected Headers headers;

        Request(String url) {
            this(url, Client.defaultClient());
        }

        Request(String url, Client client) {
            this.url = url;
            this.client = client;
        }

        public T headers(Headers headers) {
            this.headers = headers;
            return (T) this;
        }

        public Response exec() {
            return new Response();
        }
    }

    public static class BodyParamsRequest<R extends Request> extends Request<R> {
        protected BodyParams bodyParams;

        BodyParamsRequest(String url) {
            super(url);
        }

        BodyParamsRequest(String url, Client client) {
            super(url, client);
        }

        public <P extends BodyParams> R bodyParams(P bodyParams) {
            this.bodyParams = bodyParams;
            return (R) this;
        }

    }

    public static class GetRequest extends Request<GetRequest> {
        GetRequest(String url) {
            super(url);
        }

        GetRequest(String url, Client client) {
            super(url, client);
        }
    }

    public static class PostRequest extends BodyParamsRequest<PostRequest> {

        PostRequest(String url) {
            super(url);
        }

        PostRequest(String url, Client client) {
            super(url, client);
        }

    }

    public static class PutRequest extends BodyParamsRequest<PutRequest> {
        PutRequest(String url) {
            super(url);
        }

        PutRequest(String url, Client client) {
            super(url, client);
        }
    }

    public static class DeleteRequest extends BodyParamsRequest<DeleteRequest> {

        DeleteRequest(String url) {
            super(url);
        }

        DeleteRequest(String url, Client client) {
            super(url, client);
        }
    }

    public static class HeadRequest extends BodyParamsRequest<HeadRequest> {

        HeadRequest(String url) {
            super(url);
        }

        HeadRequest(String url, Client client) {
            super(url, client);
        }
    }

    public static class OptionsRequest extends BodyParamsRequest<OptionsRequest> {

        OptionsRequest(String url) {
            super(url);
        }

        OptionsRequest(String url, Client client) {
            super(url, client);
        }

    }

    public static class TraceRequest extends BodyParamsRequest<TraceRequest> {

        TraceRequest(String url) {
            super(url);
        }

        TraceRequest(String url, Client client) {
            super(url, client);
        }

    }

    public static class ConnectRequest extends BodyParamsRequest<ConnectRequest> {

        ConnectRequest(String url) {
            super(url);
        }

        ConnectRequest(String url, Client client) {
            super(url, client);
        }

    }

    public interface BodyParams {
        static FormParams form() {
            return new FormParams();
        }
        static RawParams raw(String raw) {
            return new RawParams(raw);
        }
    }

    /**
     * Body 表单传参数， key - value 形式
     */
    public static class FormParams extends HashMap<String, String> implements BodyParams {
        Map<String, File> fileFields;
        private FormParams() {
        }

        public FormParams add(String param, String value) {
            put(param, value);
            return this;
        }
        public FormParams addFileField(String param, File file) {
            if (fileFields == null) {
                fileFields = new HashMap<>();
            }
            fileFields.put(param, file);
            return this;
        }
    }

    /**
     * Body raw传参
     */
    public static class RawParams implements BodyParams {
        private RawParams() {
        }
        private String data;
        private RawParams(String data) {
            this.data = data;
        }
        public String data() {
            return data;
        }
    }

    /**
     * Http Headers
     */
    public static class Headers extends HashMap<String, String> {
        public Headers add(String key, String value) {
            if (Strings.isNotBlank(key)) {
                put(key, value);
            }
            return this;
        }
        public Headers add(Header header, String value) {
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
