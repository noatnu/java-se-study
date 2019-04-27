package cn.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CachedThreadPoolDemo {
    private volatile AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        new CachedThreadPoolDemo().isit();
    }

    public void isit() {

        Thread a = new Thread((() -> {
            synchronized (atomicInteger) {
                while (atomicInteger.get() < 15) {
                    try {
                        System.out.println("-----> " + atomicInteger.get() + " " + System.currentTimeMillis());
                        atomicInteger.incrementAndGet();
                        if (atomicInteger.get() % 2 == 0) atomicInteger.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }));

        Thread b = new Thread((() -> {
            synchronized (atomicInteger) {
                while (atomicInteger.get() < 15) {
                    System.out.println("<----- " + atomicInteger.get() + " " + System.currentTimeMillis());
                    atomicInteger.getAndIncrement();
                    if (atomicInteger.get() % 2 != 0) atomicInteger.notifyAll();
                }
            }
        }));

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(a);
        exec.execute(b);
        exec.shutdown();
    }
}
