package com.intemanagement.sdk.exception;

public class InvalidParamException extends CodedException {

    public static final int DEFAULT_INVALID_PARAM_ERROR_CODE = 500;

    private InvalidParamException(String message) {
        super(DEFAULT_INVALID_PARAM_ERROR_CODE, message);
    }

    public static InvalidParamException of(String message) {
        return new InvalidParamException(message);
    }

    public static InvalidParamException ofNoStack(String message) {
        return new NoStackInvalidParamException(message);
    }

    /**
     * 无堆栈的异常，不打印堆栈信息
     */
    private static class NoStackInvalidParamException extends InvalidParamException {

        private NoStackInvalidParamException(String message) {
            super(message);
        }

        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }
}
