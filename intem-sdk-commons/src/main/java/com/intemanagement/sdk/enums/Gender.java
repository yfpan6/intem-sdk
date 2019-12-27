package com.intemanagement.sdk.enums;

import com.intemanagement.sdk.commons.CodedMessage;

/**
 * @Author panyunfeng
 * @Date 2019-11-11
 */
public enum Gender implements CodedMessage {

    UNKNOWN((byte) 0, "未知"),
    MALE((byte) 1, "男"),
    FEMALE((byte) 2, "女");

    private byte code;
    private String message;
    Gender(byte code, String message) {
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

    public byte getByteCode() {
        return code;
    }

}
