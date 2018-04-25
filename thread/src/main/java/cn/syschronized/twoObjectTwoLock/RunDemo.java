package cn.syschronized.twoObjectTwoLock;

public class RunDemo {
    public static void main(String[] args) {
        HasSelfPrivateNum numRef1 = new HasSelfPrivateNum();
        HasSelfPrivateNum numRef2 = new HasSelfPrivateNum();

        ThreadA athread = new ThreadA(numRef1);
        athread.start();

        ThreadB bthread = new ThreadB(numRef2);
        bthread.start();
        /**
         * a set over!
         b set over!
         b num=200
         a num=100
        效果为异步,当多个对象访问多个对象,则JVM会创建多个锁
         */
    }
}
