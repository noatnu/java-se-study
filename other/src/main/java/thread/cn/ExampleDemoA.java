package thread.cn;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/7/2
 **/
public class ExampleDemoA {

    /**
     * 今天在用junit做调用其他项目组接口的登陆测试时，发现当在junit中启用多线程时，启动多线程很可能根本就不会运行。
     *  经过搜索资料，才知道原来junit会在运行完时将其管理的所有线程都释放掉。所以所有线程还没有跑就因为junit线程的释放而提前释放。
     *  当debug时，断点打在junit主线程上，延长了junit线程的存活时间，新new 的线程得以运行。所以要想在单元测试中使用多线程，
     *  应该在junit线程中增加Thread.sleep 操作。以保证其他线程有时间运行。
     * @throws InterruptedException
     */
    @Test
    public void testA()throws InterruptedException{

        Thread thread = new Thread(() -> {
            String text = StringUtils.repeat("=",20)+"> "+Thread.currentThread().getName() ;
            System.out.println(text);
        });

        Thread t2 = new Thread(() -> {
            int result = 100;
            int i = 0;
            while (i < result) {
                System.out.println("ExampleDemoA.testA"+Thread.currentThread().getId());
                i++;
            }
        }) ;
        thread.start();
        t2.start();

        Thread.sleep(3000);
    }

    @Test
    public void test2(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("================> "+Thread.currentThread().getName());
            }
        }) ;
        thread.start();
    }

    @Test
    public void test3(){
        Thread thread = new Thread() {

        } ;

        Object o = new Object(){} ;

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("<=================== "+Thread.currentThread().getName());
            }
        }){
            @Override
            public void run() {
                //子类 覆盖了父类的run method
//                System.out.println("===================> "+Thread.currentThread().getName());
                super.run();
            }
        }.start();
    }

}
