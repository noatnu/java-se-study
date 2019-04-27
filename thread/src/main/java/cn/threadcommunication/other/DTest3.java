package cn.threadcommunication.other;

public class DTest3 {
    public static void main(String[] args) {
        Object lock = new Object();
        MyThread1 myThread1 = new DTest3().new MyThread1(lock);
        MyThread2 myThread2 = new DTest3().new MyThread2(lock);
        try {
            myThread1.start();
            Thread.sleep(3000);
            myThread2.start();
        } catch (InterruptedException e) {

        }
    }

    class MyThread1 extends Thread {
        private Object lock;

        MyThread1(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    System.out.println("wait()开始 time=" + System.currentTimeMillis());
                    lock.wait();
                    System.out.println("wait()结束 time=" + System.currentTimeMillis());
                } catch (InterruptedException e) {

                }
            }
        }
    }

    class MyThread2 extends Thread {
        private Object lock;

        MyThread2(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("notify()开始 time=" + System.currentTimeMillis());
                lock.notify();
                System.out.println("notify()结束 time=" + System.currentTimeMillis());
            }
        }
    }
    /**
     wait()开始 time=1521880037697
     notify()开始 time=1521880040698
     notify()结束 time=1521880040698
     wait()结束 time=1521880040698

     Process finished with exit code 0

     */
}
