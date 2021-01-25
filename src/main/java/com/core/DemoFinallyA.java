package com.core;

import org.testng.annotations.Test;

public class DemoFinallyA {

    @Test
    public void testAdd(){
//        byte a = 127; byte b = 127; b = a + b;
//        String.intern();
    }


    private int x = 0;
    public int checkReturn() {
        try {
            // x等于1，此处不返回
            return ++x;
        } finally {
            // 返回的结果是2
            return ++x;
        }
    }

    @Test
    public void testA(){
        int aReturn = checkReturn();
        System.out.println(aReturn);
    }

}
