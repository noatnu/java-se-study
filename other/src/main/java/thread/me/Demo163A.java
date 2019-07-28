package thread.me;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/7/13
 **/
public class Demo163A {


    /**
     * 使用CountDownLatch类的await(),countDown()方法，这里选用重写的
     * await(10000,TimeUnit.MILLISECONDS);区别再去await()方法会一直阻塞到子线程执行完，带参方法会在子线程执行完或到时间后强制中断，
     */
    @Test(threadPoolSize = 2,invocationCount = 1)
    public void testA1(){
        CountDownLatch tCountDownLatch = new CountDownLatch(1);

        Thread thread = new MyThread() ;
        thread.setName("T-1");
        thread.start();//这启动了一个线程! 打印 T-1

//        thread.run();//没有启动线程，还在当前线程运行,所以不会打印T-1


        try {
            //Thread.sleep(1200);
            tCountDownLatch.await(10000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    不确定可能9个  可能27个
     */
    @Test
    public void testA2(){
        Runnable runnable = new Runnable() {
            private List<String> stringList = new ArrayList<>(3) ;
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    stringList.add(Thread.currentThread().getName()+i) ;
                }
                System.out.println(String.join(",",stringList) );
            }
        };


        //runnable 作为共享实例 , 三个线程会共同操作
        new Thread(runnable, "A").start();
        new Thread(runnable, "B").start();
        new Thread(runnable, "C").start();
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(StringUtils.repeat("=",22)+"↑"+Thread.currentThread().getName());
        }
    }

}
