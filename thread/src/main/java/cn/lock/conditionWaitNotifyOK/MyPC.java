package cn.lock.conditionWaitNotifyOK;

public class MyPC {
    private java.util.concurrent.locks.Lock lock = new java.util.concurrent.locks.ReentrantLock();
    private java.util.concurrent.atomic.AtomicInteger atomicInteger = new java.util.concurrent.atomic.AtomicInteger(10);
    private java.util.concurrent.locks.Condition condition = lock.newCondition();
    public void awaitMethod(){
        try {
            lock.lock();
            atomicInteger.getAndIncrement();//  以原子方式将当前值加 1。但是返回的是未更新的值
            System.out.println(getClass().getSimpleName()+" time:"+System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println(getClass().getSimpleName()+" time:"+System.currentTimeMillis()+" value:"+atomicInteger.get());
            condition.await();
        }catch (InterruptedException e){
            System.out.println("异常!"+e.getMessage());
        }finally {
            lock.unlock();
            System.out.println("锁释放!");
        }
    }

    public void signalMethod(){
        try {
            lock.lock();
            atomicInteger.incrementAndGet();//  以原子方式将当前值加 1。返回的是已经更新过的值
            System.out.println(getClass().getSimpleName()+" time:"+System.currentTimeMillis()+" value:"+atomicInteger.get());
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
