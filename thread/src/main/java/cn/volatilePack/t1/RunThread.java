package cn.volatilePack.t1;

public class RunThread extends Thread {
    volatile private boolean isRunable = true;

    public boolean isRunable() {
        return isRunable;
    }

    public void setRunable(boolean runable) {
        isRunable = runable;
    }

    @Override
    public void run() {
        System.out.println("进入run了");
        while (isRunable) {
            System.out.println("很好啊!");
        }
        System.out.println("RunThread.run" + "线程被停止了");
    }
}
