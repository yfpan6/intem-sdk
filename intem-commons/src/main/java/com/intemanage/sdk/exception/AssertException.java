package com.intemanage.sdk.exception;

/**
 * 断言异常，用于业务参数判断
 *
 * AssertException 含堆栈
 * NoStackAssertException 不含堆栈
 * @Author panyunfeng
 */
public class AssertException extends CodedException {

    public static final int DEFAULT_INVALID_PARAM_ERROR_CODE = 500;

    private AssertException(String message) {
        super(DEFAULT_INVALID_PARAM_ERROR_CODE, message);
    }

    public static AssertException of(String message) {
        return new AssertException(message);
    }

    public static AssertException ofNoStack(String message) {
        return new NoStackAssertException(message);
    }

    /**
     * 无堆栈的异常，不打印堆栈信息
     */
    private static class NoStackAssertException extends AssertException {

        private NoStackAssertException(String message) {
            super(message);
        }

        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }
}
