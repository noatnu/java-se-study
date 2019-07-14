package thread.me;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/7/13
 **/
public class Demo163A {

    public void testA1(){
        Thread thread = new MyThread() ;
        thread.setName("T-1");
//        thread.start();//这启动了一个线程! 打印 T-1

        thread.run();//没有启动线程，还在当前线程运行,所以不会打印T-1
    }

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
        new Thread(runnable,"A").start();
        new Thread(runnable,"B").start();
        new Thread(runnable,"C").start();
    }

    public static void main(String[] args) {
        Demo163A run = new Demo163A();

//        run.testA1();

        run.testA2();

    }

    class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(StringUtils.repeat("=",22)+"↑"+Thread.currentThread().getName());
        }
    }

}
