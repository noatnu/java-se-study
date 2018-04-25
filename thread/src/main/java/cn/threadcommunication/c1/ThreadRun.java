package cn.threadcommunication.c1;

public class ThreadRun {

    public static void main(String[] args)throws Exception {
        ThreadRun run = new ThreadRun();
        Object lock = new Object();
        INITData initData = new INITData();
        Thread a = run.a(initData,lock);
        Thread b = run.b(initData,lock);
        b.start();
        a.start();
    }

    public Thread a(INITData initData,Object lock){
        Thread thread = new Thread(()->{
            while (true){
                initData.isA(lock);
            }
        });
        return thread;
    }

    public Thread b(INITData initData,Object lock){
        Thread thread = new Thread(()->{
            while (true){
                initData.isB(lock);
            }
        });
        return thread;
    }
}
