package com.intemanagement.sdk.enums;

import com.intemanagement.sdk.commons.CodedMessage;

/**
 * @Author panyunfeng04
 * @Date 2019-11-05
 */
public enum YesOrNo implements CodedMessage {

    UNKNOWN((byte) 0, "未知"),
    YES((byte) 1, "是"),
    NO((byte) 2, "否");

    private byte code;
    private String message;

    YesOrNo(byte code, String message) {
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
