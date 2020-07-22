package com.intemanage.sdk.exception;

import com.intemanage.sdk.commons.CodedMessage;

/**
 * @Author panyunfeng
 * @Date 2019/12/18
 */
public class CodedException extends RuntimeException {

    public static final int DEFAULT_EXCEPTION_CODED = 500;

    protected int code;

    protected CodedException() {
        this.code = DEFAULT_EXCEPTION_CODED;
    }

    protected CodedException(int code, String message) {
        super(message);
        this.code = code;
    }

    protected CodedException(int code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    public static CodedException of(String message) {
        return of(DEFAULT_EXCEPTION_CODED, message, null);
    }

    public static CodedException of(String message, Throwable e) {
        return of(DEFAULT_EXCEPTION_CODED, message, e);
    }

    public static CodedException of(CodedMessage codedMessage) {
        if (codedMessage != null) {
            return new CodedException(codedMessage.getCode(), codedMessage.getMessage());
        } else {
            return new CodedException();
        }
    }

    public static CodedException of(CodedMessage codedMessage, Throwable e) {
        CodedException codedException;
        if (codedMessage != null) {
            return of(codedMessage.getCode(), codedMessage.getMessage(), e);
        } else {
            return of(DEFAULT_EXCEPTION_CODED, null, e);
        }
    }

    public static CodedException of(int code, String message) {
        return new CodedException(code, message);
    }

    public static CodedException of(int code, String message, Throwable e) {
        if (e != null) {
            return new CodedException(code, message, e);
        } else {
            return new CodedException(code, message);
        }
    }

    /**
     * 无堆栈的异常，不打印堆栈信息
     */
    public static CodedException ofNoStack(String message) {
        return new NoStackCodedException(DEFAULT_EXCEPTION_CODED, message);
    }

    /**
     * 无堆栈的异常，不打印堆栈信息
     */
    public static CodedException ofNoStack(CodedMessage codedMessage) {
        if (codedMessage != null) {
            return new NoStackCodedException(codedMessage.getCode(), codedMessage.getMessage());
        }
        return new NoStackCodedException(DEFAULT_EXCEPTION_CODED, null);
    }

    /**
     * 无堆栈的异常，不打印堆栈信息
     */
    public static CodedException ofNoStack(int code, String message) {
        return new NoStackCodedException(code, message);
    }

    /**
     * 无堆栈的异常，不打印堆栈信息
     */
    private static class NoStackCodedException extends CodedException {

        private NoStackCodedException(int code, String message) {
            super(code, message);
        }

        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

}
