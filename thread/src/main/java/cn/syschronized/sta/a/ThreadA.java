package cn.syschronized.sta.a;

public class ThreadA extends Thread {
    private ServiceStatic serviceStatic;

    public ThreadA(ServiceStatic serviceStatic) {
        this.serviceStatic = serviceStatic;
    }

    @Override
    public void run() {
        serviceStatic.printA();
    }
}
