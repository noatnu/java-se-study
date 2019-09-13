package thread.me;

import org.testng.annotations.Test;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/8/10
 **/
public class DemoX {

    @Test
    public void testA(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    //线程要处理的任务
                    //如果耗时较长的任务,可在在任务中间要退出的部分增加isInterrupted()的判断
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        //退出循环
                        break;
                    }
                }
                System.out.println("thread exits.");
                //关闭打开的资源,退出线程
            }
        }, "T-1") ;
        thread.start();
        thread.interrupt();

    }

    public synchronized void methodA(){

    }

}
