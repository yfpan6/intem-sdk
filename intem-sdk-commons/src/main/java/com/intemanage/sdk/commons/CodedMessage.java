package com.intemanage.sdk.commons;

/**
 * @Author panyunfeng
 * @Date 2019-11-11
 */
public interface CodedMessage {
    int getCode();
    String getMessage();

    static CodedMessage of(int code, String message) {
        return new SimpleImpl(code, message);
    }

    class SimpleImpl implements CodedMessage {

        protected int code;
        protected String message;

        public SimpleImpl() {
        }

        public SimpleImpl(int code, String message) {
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

        public SimpleImpl code(int code) {
            this.code = code;
            return this;
        }

        public SimpleImpl message(String message) {
            this.message = message;
            return this;
        }
    }
}
