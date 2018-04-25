package cn.lock.conditionWaitNotifyOK;

public class DemoRun {
    public static void main(String[] args) {
        MyPC myPC = new MyPC();
        Thread thread1 = new Thread(new ThreadA(myPC));
        Thread thread2 = new Thread(new ThreadB(myPC));
        thread1.start();
        thread2.start();
    }
}
