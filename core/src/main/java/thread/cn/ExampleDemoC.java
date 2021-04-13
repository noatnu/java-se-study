package thread.cn;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/7/3
 **/
public class ExampleDemoC {

    public static void main(String[] args) {
        test2();
    }

    public static void test2() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        MyThread myThread = new MyThread();
        executorService.execute(myThread);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.interrupt();
        System.out.println("线程是否被中断：" + myThread.isInterrupted());//true
    }

    public static void test1() {
        MyThread myThread = new MyThread();
        myThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.interrupt();
        System.out.println("线程是否被中断：" + myThread.isInterrupted());//true
    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("线程启动了");
        while (!this.isInterrupted()) {
            System.out.println("正在执行..." + System.currentTimeMillis());
        }
        System.out.println("线程结束了");

    }
}
