package com.intemanage;

import com.intemanage.sdk.httpclient.Http;

/**
 * @Author panyunfeng04
 * @Date 2020-07-24
 */
public class ApacheHttpClientTest {

    public static void main(String[] args) {
        Http.doGet("").headers(null).exec();
        Http.doPost("").headers(null).exec();
        Http.doPut("")
                .headers(new Http.Headers(){{
                    add("", "");
                }})
                .bodyParams(Http.BodyParams.raw(""))
                .exec();
    }
}
