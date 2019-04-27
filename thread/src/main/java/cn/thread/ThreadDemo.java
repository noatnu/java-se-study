package cn.thread;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.help.Zhou_String;

public class ThreadDemo {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        new ThreadDemo().testYield();
    }

    /**
     * 直接继承 Thread 实现多线程
     */
    @Test
    public void isThread() {
        class Dthread extends Thread {
            public Dthread(String name) {
                super(name);
            }

            @Override
            public void run() {
                logger.info("thread is start! " + currentThread().getName());//currentThread 可以返回代码段正在被谁调用的信息
            }
        }
        new Dthread(Zhou_String.toMath(4)).start();
    }

    /**
     * 通过 Runnable 接口 实现多线程
     */
    @Test
    public void isRunnable() {
        new Thread(() -> {
            logger.info("thread is start!");
        }).start();
    }

    /**
     * 判断线程是否处理活动状态 (ps 线程是否已经启动并且尚未终止)
     */
    @Test
    public void isAlive() {
        Thread t = new Thread(() -> {
            logger.info("thread is run!");
        });
        System.out.println("begin == " + t.isAlive());
        t.start();
        System.out.println("end == " + t.isAlive());
    }

    /**
     * 线程 在指定的时间内让正在执行的线程 暂停执行 这里正在执行的线程指的是currentThread()返回的线程
     */
    @Test
    public void sleep() {
        Thread thread = new Thread(() -> {
            logger.info("线程正在执行!");
            try {
                logger.info("休眠开始!");
                Thread.sleep(2000);
                logger.info("休眠结束!");
            } catch (InterruptedException e) {
                logger.error("异常!");
            }
        });
        thread.start();
    }

    /**
     * interrupt() 在当前线程打上标记并不是真正的突然马上停止 不想stop()那样马上停止(已经废弃)
     */
    @Test
    public void interrupt() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 500000; i++) {
                logger.info("i= " + (i + 1));
            }
        }, "" + Zhou_String.toLowerCase(5));
        thread.start();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            logger.error("异常!");
        }
        logger.info("thread name:" + thread.getName() + " id:" + thread.getId());
//        thread.interrupt();
        thread.stop();
        /**
         * 15:51:35,182 [ main ] [ INFO ]:83 - thread name:kalxj id:11
         * 15:51:35,201 [ kalxj ] [ INFO ]:74 - i= 20710
         */
    }

    /**
     * interrupted() static method 当前线程是否已经中断(会清除标志)
     * isInterrupted() is not static method 当前线程是否已经中断
     */
    @Test
    public void is_interrupted() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 500000; i++) {
                logger.info("i= " + (i + 1));
            }
        }, "" + Zhou_String.toLowerCase(5));
        try {
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
            int i = 1;
            logger.info("是否停止" + thread.getName() + (i++) + "!" + thread.interrupted());
            logger.info("是否停止" + thread.getName() + (i++) + "!" + thread.interrupted());
        } catch (InterruptedException e) {
            logger.error("异常!");
        }
        /**
         * 16:11:47,886 [ main ] [ INFO ]:111 - 是否停止hdnzo1!false
         * 16:11:47,887 [ main ] [ INFO ]:112 - 是否停止hdnzo2!false
         * 因为当前线程是main所以从未中断过!
         */
        //让 main线程产生中断效果
        Thread.currentThread().interrupt();
        int i = 1;
        logger.info("是否停止" + thread.getName() + (i++) + "!" + thread.interrupted());
        logger.info("是否停止" + thread.getName() + (i++) + "!" + thread.interrupted());
        /**
         * 16:16:50,736 [ main ] [ INFO ]:124 - 是否停止stymm1!true
         * 16:16:50,740 [ main ] [ INFO ]:125 - 是否停止stymm2!false
         * 第一个打印语句确实准确判断了当前线程已经中断但是第二条语句居然没有准确判断
         * 这是因为interrupted()执行后具有将状态标志设为false的功能
         */
        Thread.currentThread().interrupt();
        i = 1;
        logger.info("是否停止 -->" + thread.getName() + (i++) + "!" + thread.isInterrupted());
        logger.info("是否停止 -->" + thread.getName() + (i++) + "!" + thread.isInterrupted());
        /**
         *16:23:32,540 [ main ] [ INFO ]:134 - 是否停止 -->ijltd1!false
         *16:23:32,541 [ main ] [ INFO ]:135 - 是否停止 -->ijltd2!false
         */
        //isInterrupted()和interrupt()功能差不多但是不具备清除标识符的作用
    }

    /**
     * 异常状态也是可以停止的哦!
     */
    @Test
    public void testException() {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 500000; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        logger.debug("已经是停止状态!");
                        throw new InterruptedException();
                    }
                    logger.info(Thread.currentThread().getName() + " i= " + (i + 1));
                }
            } catch (InterruptedException e) {
                logger.error("异常!");
            }

        }, "" + Zhou_String.toLowerCase(5));
        try {
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
        }
        /*
         *16:39:22,084 [ dvhvh ] [ INFO ]:155 - dvhvh i= 31648
         *16:39:22,084 [ dvhvh ] [ ERROR ]:158 - 异常!
         * 线程在异常状态下停止*/

    }

    /**
     * 在睡眠中停止
     */
    @Test
    public void testSleepStop() {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 100000; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        logger.debug("已经是停止状态!");
                    }
                    logger.info(Thread.currentThread().getName() + " i= " + (i + 1));
                }
                logger.info("run begin");
                Thread.sleep(200000000);
                logger.info("run end");
            } catch (InterruptedException e) {
                logger.error("异常!");
            }

        }, "" + Zhou_String.toLowerCase(5));
        thread.start();
        thread.interrupt();
        logger.info("end!");
        /**
         * 16:44:17,066 [ main ] [ INFO ]:195 - end!
         16:44:17,066 [ celtq ] [ INFO ]:183 - celtq i= 1
         16:44:17,072 [ celtq ] [ INFO ]:183 - celtq i= 2
         ⇧⇧⇩⇩
         16:44:17,090 [ celtq ] [ INFO ]:183 - celtq i= 92
         16:44:17,090 [ celtq ] [ INFO ]:183 - celtq i= 93
         一共就只打印灵93行 其实sleep根本就没有等到其恢复线程就已经停止了
         */
    }

    /**
     * 暴力停止 一般情况不要使用 容易使数据遭到破坏
     * stop() 释放不良的锁
     */
    @Test
    public void testStop() {
        Thread thread = new Thread(() -> {
            try {
                int i = 0;
                while (true) {
                    if (!Thread.currentThread().isInterrupted()) {
                        if (Thread.currentThread().isInterrupted()) {
                            logger.debug("已经是停止状态!");
                        }
                        logger.info(Thread.currentThread().getName() + " i= " + (++i) + Thread.currentThread().isInterrupted());
                        Thread.sleep(2000);
                    }
                }
            } catch (InterruptedException e) {
                logger.error("异常!->-> " + e.getMessage());
            }
        });
        try {
            thread.start();
            Thread.sleep(1000);
            thread.stop();
        } catch (Exception e) {
            logger.error("异常!-- " + e.getMessage());
        }
    }

    /**
     * 使用return 停止线程
     */
    @Test
    public void isReturn() {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (true) {
                if (!Thread.currentThread().isInterrupted()) {
                    if (Thread.currentThread().isInterrupted()) {
                        logger.debug("已经是停止状态!");
                        return;
                    }
                    logger.info(Thread.currentThread().getName() + " i= " + (++i) + Thread.currentThread().isInterrupted());
                }
            }

        });
        try {
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
        } catch (Exception e) {
            logger.error("异常!-- " + e.getMessage());
        }
    }

    /**
     * 暂停与恢复
     */
    @Test
    public void testSuspend_resume() {
        class MyThread extends Thread {
            private long i = 0;

            public MyThread(String name) {
                super(name);
            }

            public long getI() {
                return i;
            }

            public void setI(long i) {
                this.i = i;
            }

            @Override
            public void run() {
                while (true) {
                    i++;
                }

            }
        }
        try {
            MyThread thread = new MyThread(Zhou_String.toLowerCase(5));
            int NUM = 1000 * 4;
            thread.start();
            Thread.sleep(NUM);
            //A 段

            thread.suspend();//暂停
            logger.info("A=" + System.currentTimeMillis() + " i=" + thread.getI());
            Thread.sleep(NUM);
            logger.info("A=" + System.currentTimeMillis() + " i=" + thread.getI());

            //B段
            thread.resume();//恢复
            System.out.println("程序恢复执行!");
            Thread.sleep(NUM);

            //C段
            thread.suspend();//暂停
            logger.info("C=" + System.currentTimeMillis() + " i=" + thread.getI());
            Thread.sleep(NUM);
            logger.info("C=" + System.currentTimeMillis() + " i=" + thread.getI());
        } catch (InterruptedException e) {
            logger.error("异常!" + e.getMessage());
        }

    }

    /**
     * suspend 对公共对象独占
     */
    @Test
    public void testSuspend_resume_synchronized() {
        class MyObject {
            private String username = "1";
            private String password = "11";

            public void setValue(String u, String p) {
                this.username = u;
                if (Thread.currentThread().getName().equals("a")) {
                    System.out.println("停止a线程");
                    Thread.currentThread().suspend();
                }
                this.password = p;
            }

            public void printUsernamePassword() {
                System.out.println("username " + username + " password " + password);
            }
        }
        final MyObject myObject = new MyObject();
        Thread thread = new Thread(() -> {
            myObject.setValue("a", "aa");
        });
        thread.setName("a");
        try {
            thread.start();
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }
        new Thread(() -> {
            myObject.printUsernamePassword();
        }).start();
        //在 thread 中已经修改过但是后面的线程调用数据 没有与修改过的同步
        //因此suspend()需要小心同步问题
    }

    /**
     * yield()作用是放弃当前cpu资源，将它让给其他的任务去占用CPU执行时间,但放弃的时间不确定
     * 有可能刚刚放弃马上就又获得灵CPU资源
     * 不能用junit5测试
     */
    @Test
    public void testYield() {
        Thread thread = new Thread(() -> {
            long beginTime = System.currentTimeMillis();
            int count = 0;
            for (int i = 0; i < 50000000; i++) {
                Thread.yield();
                count += (i + 1);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("时间:" + (endTime - beginTime) + " 毫秒");
        });
        thread.start();
        /**改变 Thread.yield()使它被注释
         * 时间:23 毫秒
         * 时间:21790 毫秒
         */
    }

    /**
     * 守护线程 就有点类似于保姆
     */
    @Test
    public void testDaemon() {
        Thread thread = new Thread(() -> {
            int count = 0;
            for (int i = 0; i < 50000000; i++) {
                System.out.println("name:" + Thread.currentThread().getName() + " i=" + i);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("end!");
    }

    @Test
    public void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        });
        try {
            t1.setName("Bob");
            t2.setName("Lee");
            t1.start();
            t1.join();
            t2.start();
        } catch (InterruptedException e) {
            logger.error("异常!");
        }
        /**join的意思是使得放弃当前线程的执行，并返回对应的线程，例如下面代码的意思就是：
         程序在 junit 线程中调用t1线程的join方法，则 junit 线程放弃cpu控制权，并返回t1线程继续执行直到线程t1执行完毕
         所以结果是t1线程执行完后，才到主线程执行，相当于在junit线程中同步t1线程，t1执行完了，junit线程才有执行的机会

         join方法的原理就是调用相应线程的wait方法进行等待操作的
         */
    }


}
