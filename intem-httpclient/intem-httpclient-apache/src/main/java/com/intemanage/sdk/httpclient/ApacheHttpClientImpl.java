package com.intemanage.sdk.httpclient;

import com.intemanage.sdk.utils.Asserts;
import com.intemanage.sdk.utils.Collections;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ApacheHttpClientImpl extends Http.Client {
    private HttpClient httpClient;

    public ApacheHttpClientImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Http.Response get(Http.GetRequest getRequest) {
        Asserts.isNotNull(httpClient, "The apache HttpClient is null.");
        Asserts.isNotNull(getRequest, "The param Http.GetRequest is null.");
        HttpGet httpGet = new HttpGet(getRequest.url);
        addHeaders(httpGet, getRequest.headers);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            response.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
        return null;
    }

    @Override
    public Http.Response post(Http.PostRequest postRequest) {
        Asserts.isNotNull(httpClient, "The apache HttpClient is null.");
        Asserts.isNotNull(postRequest, "The param Http.PostRequest is null.");
        HttpPost httpPost = new HttpPost(postRequest.url);
        addHeaders(httpPost, postRequest.headers);
        try {
            HttpEntity httpEntity = buildHttpEntity(postRequest.bodyParams);
            if (httpEntity != null) {
                httpPost.setEntity(httpEntity);
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Http.Response put(Http.PutRequest putRequest) {
        Asserts.isNotNull(httpClient, "The apache HttpClient is null.");
        return null;
    }

    @Override
    public Http.Response delete(Http.DeleteRequest deleteRequest) {
        Asserts.isNotNull(httpClient, "The apache HttpClient is null.");
        return null;
    }

    @Override
    public Http.Response head(Http.HeadRequest headRequest) {
        Asserts.isNotNull(httpClient, "The apache HttpClient is null.");
        return null;
    }

    @Override
    public Http.Response options(Http.OptionsRequest optionsRequest) {
        Asserts.isNotNull(httpClient, "The apache HttpClient is null.");
        return null;
    }

    @Override
    public Http.Response trace(Http.TraceRequest traceRequest) {
        Asserts.isNotNull(httpClient, "The apache HttpClient is null.");
        return null;
    }

    @Override
    public Http.Response connect(Http.ConnectRequest connectRequest) {
        Asserts.isNotNull(httpClient, "The apache HttpClient is null.");
        return null;
    }

    private void addHeaders(HttpMessage httpMessage, Http.Headers headers) {
        if (Collections.isEmpty(headers)) {
            return;
        }

        headers.forEach((header, value) -> {
            httpMessage.addHeader(header, value);
        });
    }

    private HttpEntity buildHttpEntity(Http.BodyParams bodyParams) {
        HttpEntity httpEntity = null;
        if (bodyParams != null) {
            if (bodyParams instanceof Http.RawParams) {
                StringEntity stringEntity = new StringEntity(((Http.RawParams) bodyParams).data(), "UTF-8");
                httpEntity = stringEntity;
            } else if (bodyParams instanceof Http.FormParams) {
                Http.FormParams formParams = (Http.FormParams) bodyParams;
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                formParams.forEach((param, value) -> builder.addTextBody(param, value));
                Map<String, File> fileFields = formParams.fileFields;
                if (Collections.isNotEmpty(fileFields)) {
                    fileFields.forEach((fileParam, file) -> builder.addPart(fileParam, new FileBody(file)));
                }
                httpEntity = builder.build();
            }
        }
        return httpEntity;
    }
}
