package com.init;

import java.util.Random;

/**
 * Created by zhou on 17-12-6.
 */
public class Demo3 {

    Demo3_A demo = new Demo3_A("成员变量执行");

    {
        new Demo3_A("成员变量代码块执行");
    }


    public static void main(String[] args) {
        new Demo3();
    }
}

class Demo3_A {

    public Demo3_A(String s) {
        System.out.println("Demo3_A.Demo3_A " + s + new Random(47).nextInt(20));
    }
}
/**
 * 结论：
 * 成员变量代码块执行初始化和成员变量初始化执行初始化顺序一致,实际顺序参照代码树即可
 * */
