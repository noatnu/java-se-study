package com.init;

/**
 * @author zhou
 * Created by zhou on 17-12-6.
 */
public class Demo {

    public static void main(String[] args) {
        new TestSub();
    }

}
class Test {
    {
        System.out.println("父类static 块 1  执行");
    }

    static Sample staticSam1 = new Sample("父类 静态成员staticSam1初始化");
    Sample sam1 = new Sample("父类 sam1 成员初始化");

    static Sample staticSam2 = new Sample("父类 静态成员 staticSam2 初始化");

    static {
        System.out.println("父类 static 块 2  执行");
    }

    public Test() {
        System.out.println("父类 Test默认构造函数被调用");
    }

    Sample sam2 = new Sample("父类 sam2成员初始化");
}
class TestSub extends Test {
    static Sample staticSamSub = new Sample("子类 静态成员staticSamSub初始化");

    TestSub() {
        System.out.println("子类 TestSub 默认构造函数被调用");
    }

    Sample sam1 = new Sample("子类 sam1成员初始化");
    static Sample staticSamSub1 = new Sample("子类 静态成员staticSamSub1初始化");

    static {
        System.out.println("子类 static 块  执行");
    }

    Sample sam2 = new Sample("子类 sam2成员初始化");
}

/**
 由此得出Java初始化顺序结论：
  静态 情况下
 父类静态成员 > 父类静态块 >子类静态成员>子类静态块
 非静态 情况下
 父类成员变量 > 父类构造器 > 子类成员变量 > 字类构造器
 */