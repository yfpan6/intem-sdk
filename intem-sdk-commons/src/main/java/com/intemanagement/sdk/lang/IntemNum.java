package com.intemanagement.sdk.lang;

/**
 * @Author panyunfeng
 * @Date 2019/12/18
 */
public class IntemNum<T extends Number> {

    public T num;

    public T google;

    public boolean lessThan(T t) {
        if (num == null) {
            return true;
        }
        if (t == null) {
            return false;
        }

        return false;
    }

    public static void main(String[] args) {
        Integer i = new Integer(1);
        Integer j = new Integer(1);
        System.out.println(i == j.intValue());
        System.out.println(i.equals(j));
        System.out.println(i >= j);
        System.out.println(i <= j);
    }
}
