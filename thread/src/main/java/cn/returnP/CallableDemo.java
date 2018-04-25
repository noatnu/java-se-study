package cn.returnP;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new HandlerThreadFactory());
        ArrayList<Future<String>> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(executorService.submit(new TaskWithResult()));
        }
        result.forEach(stringFuture -> {
            try {
                System.out.println("string:"+stringFuture.get());
            }catch (ExecutionException e){
                e.printStackTrace();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }

}

class TaskWithResult implements Callable<String> {

    private AtomicReference<String> atomicReference = new AtomicReference<>();
    @Override
    public String call() throws Exception {
        atomicReference.set("time:"+System.currentTimeMillis());
        return atomicReference.get();
    }
}

class HandlerThreadFactory implements ThreadFactory{
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        return t;
    }

    class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("异常了 "+e.getMessage());
        }
    }
}
