package cn.threadcommunication.p_r.test;

public class ThreadC extends Thread {

    private Consumer r;

    public ThreadC(Consumer r) {
        super();
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.getValue();
        }
    }

}
