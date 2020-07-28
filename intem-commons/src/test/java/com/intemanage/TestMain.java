package com.intemanage;

/**
 * @Author panyunfeng
 * @Date 2019/12/21
 */
public class TestMain {
/*
null:false
4:false
6:true
10:true
12:false
13:true
40:false
45:true
50:false
100:false
120:true
130:true
150:true
200:true
 */
    public static void main(String[] args) {

        System.out.println(new Integer(100).doubleValue() == new Double(100).doubleValue());
        //System.out.println(a != null || a > 100);
    }
}
