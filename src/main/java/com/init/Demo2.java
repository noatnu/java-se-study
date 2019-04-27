package com.init;

/**
 * Created by zhou on 17-12-6.
 */
public class Demo2 {


    /**
     * sample 和 sample1顺序实际初始化顺序是一致的
     */
    static Sample sample1 = new Sample("sample1 static成员初始化");

    static {
        System.out.println("静态块执行");
        Sample sample = new Sample("sample initializer static 静态块初始化");
    }

    Sample sample2 = new Sample("sample2 成员初始化");

    public Demo2() {
        System.out.println("Demo2.Demo2 constructors");
    }

    public static void main(String[] args) {
        new Demo2();
    }
}


/**
 * 结论:
 * a static成员最先初始化
 * b static块在static成员之后执行初始化
 * c 成员变量初始化
 * d 构造器初始化
 * 注意:sample1和static{xxx}其实质执行初始化顺序是一致实际的初始化顺序只与代码树有关
 */