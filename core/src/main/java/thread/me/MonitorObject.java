package thread.me;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/8/11
 **/
public class MonitorObject {

    private Object object = new Object();

    public void doA()throws InterruptedException{
        synchronized (object){
            System.out.println("1");
            object.wait();
            System.out.println("2");
        }
    }

    public void doB()throws InterruptedException{
        synchronized (object){
            System.out.println("3");
            object.notify();
            System.out.println("4");
        }
    }

    public static void main(String[] args) {
        MonitorObject mo = new MonitorObject();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mo.doA();
                } catch (InterruptedException e) {

                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mo.doB();
                } catch (InterruptedException e) {

                }
            }
        }, "t2");

        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }
        t2.start();

    }

}
