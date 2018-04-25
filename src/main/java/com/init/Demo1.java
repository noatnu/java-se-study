package com.init;

/**
 * Created by zhou on 17-12-6.
 * 静态块先于构造其初始化
 */
public class Demo1 {
    {
        System.out.println("Demo1.instance initializer");
    }

    public Demo1() {
        System.out.println("Demo1.Demo1");
    }

    public static void main(String[] args) {
        new Demo1();
    }
}
