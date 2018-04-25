
同步代码块 呈同步效果
MyObject object 作为同步锁是关键
synchronized (object) {
    try {
        System.out.println("testMethod1 ____getLock time="
                + System.currentTimeMillis() + " run ThreadName="
                + Thread.currentThread().getName());
        Thread.sleep(2000);
        System.out.println("testMethod1 releaseLock time="
                + System.currentTimeMillis() + " run ThreadName="
                + Thread.currentThread().getName());
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}