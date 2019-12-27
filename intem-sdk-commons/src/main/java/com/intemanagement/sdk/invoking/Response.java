package com.intemanagement.sdk.invoking;

import com.intemanagement.sdk.commons.CodedMessage;
import lombok.Getter;

/**
 * code = 200 表示成功
 * 非200 表示失败
 *
 * @Author panyunfeng
 * @Date 2019/12/15
 */
@Getter
public final class Response<T> {

    /**
     * 复用了http的状态码 及 含义
     */
    public enum State implements CodedMessage {
        SUCCESS(200, "Success"),
        FAILURE(500, "Failure"),
        FORBIDDEN(403, "Forbidden"),
        SERVICE_UNAVAILABLE(503, "Service Unavailable");

        private int code;
        private String message;

        State(int code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

    /**
     * 状态码
     */
    public int code;

    /**
     * 消息
     */
    public String message;

    /**
     * 数据
     */
    public T data;

    private Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return code == State.SUCCESS.getCode();
    }

    public boolean isFail() {
        return code != State.SUCCESS.getCode();
    }

    public static <T> Response<T> of(int code, String message, T data) {
        return new Response<>(code, message, data);
    }

    public static <T> Response<T> of(CodedMessage codedMessage) {
        return new Response<>(codedMessage.getCode(), codedMessage.getMessage(), null);
    }

    public static <T> Response<T> of(CodedMessage codedMessage, T data) {
        return new Response<>(codedMessage.getCode(), codedMessage.getMessage(), data);
    }

    public static <T> Response<T> success() {
        return of(State.SUCCESS, null);
    }

    public static <T> Response<T> success(T data) {
        return of(State.SUCCESS, data);
    }

    public static Response fail(int code) {
        return new Response(code, State.FAILURE.getMessage(), null);
    }

    public static Response fail(int code, String message) {
        return new Response(code, message, null);
    }

    public static <T> Response<T> fail(CodedMessage codedMessage) {
        return new Response<>(codedMessage.getCode(), codedMessage.getMessage(), null);
    }

}
