package cn.volatilePack.t1;

public class RUNDemo {
    public static void main(String[] args) throws InterruptedException {
        RunThread thread = new RunThread();
        thread.start();
        Thread.sleep(1000);
        thread.setRunable(false);
    }
}
