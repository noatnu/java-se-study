package cn.threadLocal.t1;

import cn.threadLocal.ToolsLocal;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadB implements Runnable {
    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                atomicInteger.set(i);
                atomicInteger.incrementAndGet();
//                Object value = this.getClass().getSimpleName()+"  "+(i+1);
                Object value = this.getClass().getSimpleName() + "  " + atomicInteger.get();
                ToolsLocal.t1.set(value);
                System.out.println(getClass().getSimpleName() + " " + ToolsLocal.t1.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
