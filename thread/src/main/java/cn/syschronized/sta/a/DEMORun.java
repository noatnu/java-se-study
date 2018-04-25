package cn.syschronized.sta.a;

public class DEMORun {
    public static void main(String[] args) {
        ServiceStatic serviceStatic = new ServiceStatic();
        ThreadA a = new ThreadA(serviceStatic);
        ThreadB b = new ThreadB(serviceStatic);
        ThreadC c = new ThreadC(serviceStatic);
        a.setName("a");
        b.setName("b");
        c.setName("c");
        a.start();
        b.start();
        c.start();
        /**
         * 异步的原因是持有不同的锁一个是对象锁而另一个是对象锁
         */
    }
}
