package cn.threadLocal.t2;

public class RunDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadA a = new ThreadA();
        a.start();
        Thread.sleep(1000);
        ThreadB b = new ThreadB();
        b.start();
    }
}
