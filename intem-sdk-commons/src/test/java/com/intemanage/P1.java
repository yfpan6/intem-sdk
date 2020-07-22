package com.intemanage;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class P1 extends P2 {
    private P1 p;
    private String f;
    private Integer g;
    private Boolean boolB;

    public static void main(String[] args) throws Exception {
        Method[] methods = P1.class.getMethods();
        System.out.println(methods);
        Method method = P1.class.getMethod("getP");
        System.out.println(method ==P1.class.getMethod("getP") );
        System.out.println(P1.class.getMethod("getP").hashCode());
    }
}

@Getter
@Setter
class P2 extends P3 {
    private Byte h;
}

@Getter
@Setter
class P3 extends P4 {
    protected String c;
    protected List<?> d;
    private int e;
    private boolean boolA;
}

@Getter
@Setter
class P4 {
    private P4 p;
    private String a;
    private Date b;
    private Boolean boolC;
}
