package thread.me;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/8/10
 **/
public class MyTask {

    private Object object = new Object();

    public void doA() throws InterruptedException {
        synchronized (object) {//获得锁
            object.wait();//释放锁,待其它线程notify或者notifyAll后重新获得锁后继续运行
        }//释放锁
    }

    public void doB() {
        synchronized(object){//获得锁
            object.notify(); //通知一个被wait此监视器的线程
        }//释放锁
    }

}
