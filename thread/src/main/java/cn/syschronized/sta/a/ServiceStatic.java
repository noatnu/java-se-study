package cn.syschronized.sta.a;

public class ServiceStatic {

    synchronized public static void printA() {
        try {
            System.out.println("线程名称为:" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入printA");
            Thread.sleep(3000);
            System.out.println("线程名称为:" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开printA");
        } catch (InterruptedException e) {
            System.out.println("异常:" + e.getMessage());
        }
    }

    synchronized public static void printB() {
        try {
            System.out.println("线程名称为:" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入printB");
            Thread.sleep(3000);
            System.out.println("线程名称为:" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开printB");
        } catch (InterruptedException e) {
            System.out.println("异常:" + e.getMessage());
        }
    }

    synchronized public void printC() {
        try {
            System.out.println("线程名称为:" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入printC");
            Thread.sleep(3000);
            System.out.println("线程名称为:" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开printC");
        } catch (InterruptedException e) {
            System.out.println("异常:" + e.getMessage());
        }
    }
}
