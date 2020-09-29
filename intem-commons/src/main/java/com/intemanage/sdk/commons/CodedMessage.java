package com.intemanage.sdk.commons;

/**
 * 编码过的消息
 * @Author panyunfeng
 * @Date 2019-11-11
 */
public interface CodedMessage {

    /**
     * 消息码
     * @return
     */
    int getCode();

    /**
     * 消息内容
     * @return
     */
    String getMessage();

    static SimpleImpl of(int code, String message) {
        return new SimpleImpl(code, message);
    }

    class SimpleImpl implements CodedMessage {

        protected int code;
        protected String message;

        private SimpleImpl(int code, String message) {
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
}
