package cn.lock.conditionWaitNotifyOK;

public class ThreadA implements Runnable {
    private MyPC myPC;

    public ThreadA(MyPC myPC) {
        this.myPC = myPC;
    }

    @Override
    public void run() {
        myPC.awaitMethod();
    }
}
