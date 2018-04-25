package cn.lock.conditionWaitNotifyOK;

public class ThreadB implements Runnable {
    private MyPC myPC;

    public ThreadB(MyPC myPC) {
        this.myPC = myPC;
    }

    @Override
    public void run() {
        myPC.signalMethod();
    }
}
