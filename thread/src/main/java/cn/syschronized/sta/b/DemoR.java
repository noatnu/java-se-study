package cn.syschronized.sta.b;

public class DemoR {
    public static void main(String[] args) {

        Service service1 = new Service();
        Service service2 = new Service();

        ThreadA a = new ThreadA(service1);
        a.setName("A");
        a.start();

        ThreadB b = new ThreadB(service2);
        b.setName("B");
        b.start();
/**
 * 线程名称为：A在1521787600731进入printA
 线程名称为：A在1521787603731离开printA
 线程名称为：B在1521787603731进入printB
 线程名称为：B在1521787603731离开printB
 同样是异步说明锁的是class
 */
    }

}
