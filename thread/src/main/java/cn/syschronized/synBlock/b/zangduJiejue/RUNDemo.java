package cn.syschronized.synBlock.b.zangduJiejue;

public class RUNDemo {
    public static void main(String[] args) {
        try {
            MyOneList list = new MyOneList();

            MyThread1 thread1 = new MyThread1(list);
            thread1.setName("A");
            thread1.start();

            MyThread2 thread2 = new MyThread2(list);
            thread2.setName("B");
            thread2.start();

            Thread.sleep(6000);

            System.out.println("listSize=" + list.getSize());
        } catch (InterruptedException e) {
            System.out.println("异常!");
        }
    }
}
