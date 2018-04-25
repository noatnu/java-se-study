package cn.syschronized.sta.a;

public class ThreadC extends Thread {
    private ServiceStatic serviceStatic;

    public ThreadC(ServiceStatic serviceStatic) {
        this.serviceStatic = serviceStatic;
    }

    @Override
    public void run() {
        serviceStatic.printC();
    }
}
