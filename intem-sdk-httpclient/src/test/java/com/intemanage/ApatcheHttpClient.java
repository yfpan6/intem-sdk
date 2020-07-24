package com.intemanage;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;

/**
 * @Author panyunfeng04
 * @Date 2020-07-24
 */
public class ApatcheHttpClient {

    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://www.baidu.com?abc=nihao&dfg=nihao");
        try {
            HttpResponse response = httpclient.execute(get);
            System.out.println(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        MultipartEntityBuilder.create()
                .addPart("text", new FileBody(new File(""))).addTextBody("", "").build();
        HttpPost post = new HttpPost();
        post.getEntity();
    }
}
