package cn.syschronized.sta.a;

public class ThreadB extends Thread {
    private ServiceStatic serviceStatic;

    public ThreadB(ServiceStatic serviceStatic) {
        this.serviceStatic = serviceStatic;
    }

    @Override
    public void run() {
        serviceStatic.printB();
    }
}
