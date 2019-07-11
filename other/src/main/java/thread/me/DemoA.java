package thread.me;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author zch
 * @Description
 * @createDate 2019/7/11
 **/
public class DemoA {

    /**
     * 不能
     */
    public void testA(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(StringUtils.repeat("=",20)+"||"+Thread.currentThread().getName()+StringUtils.repeat("-",10));
            }
        };

        MyThread myThread = new MyThread(runnable){
            @Override
            public void run() {
                super.run();
            }
        } ;

        myThread.start();
    }

    /**
     * 不能
     */
    public void testB(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(StringUtils.repeat("=",20)+"||"+Thread.currentThread().getName()+StringUtils.repeat("-",10));
            }
        };

        MyThread myThread = new MyThread(runnable);

        myThread.start();
    }

    /**
     * 不能
     */
    public void testC(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(StringUtils.repeat("=",20)+"||"+Thread.currentThread().getName()+StringUtils.repeat("-",10));
            }
        };

        MyThread myThread = new MyThread(runnable);

        myThread.run();
    }

    /**
     * 能够执行
     */
    public void testD(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(StringUtils.repeat("=",20)+"||"+Thread.currentThread().getName()+StringUtils.repeat("-",10));
            }
        };
        runnable.run();
    }

    /**
     * 能够执行
     */
    public void testE(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(StringUtils.repeat("=",20)+"||"+Thread.currentThread().getName()+StringUtils.repeat("-",10));
            }
        };
        MyThread2 thread = new MyThread2(runnable);

        thread.start();
    }

    /**
     * 能够执行
     */
    public void testG(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(StringUtils.repeat("=",20)+"||"+Thread.currentThread().getName()+StringUtils.repeat("-",10));
            }
        };
        MyThread2 thread = new MyThread2(runnable){
            @Override
            public void run() {
                super.run();
            }
        };

        thread.start();
    }

    /**
     * 不能执行
     */
    public void testH(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(StringUtils.repeat("=",20)+"||"+Thread.currentThread().getName()+StringUtils.repeat("-",10));
            }
        };
        MyThread2 thread = new MyThread2(runnable){
            @Override
            public void run() {
                //不再调用父类的方法
            }
        };

        thread.start();
    }

    public static void main(String[] args) {
        DemoA demoA = new DemoA();
        demoA.testH();
    }

    class MyThread extends Thread{
        private Runnable runnableA;

        MyThread(Runnable runnableA) {
            this.runnableA = runnableA;
        }

        @Override
        public void run() {
            super.run();
        }

    }

    class MyThread2 extends Thread{
        private Runnable runnableA;

        MyThread2(Runnable runnableA) {
            this.runnableA = runnableA;
        }

        @Override
        public void run() {
            if (runnableA != null){
                runnableA.run();
            }
        }
    }

}
