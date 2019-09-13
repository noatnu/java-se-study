package thread.me;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/8/11
 **/
public class Sync {

    public void doA(){
        synchronized (this){
            //A      监视器Sync.this
        }
    }

    public synchronized void doB1(){
        //B1 监视器Sync.this
    }

    public synchronized void doB2(){
        //B2 监视器Sync.this
    }

    public static synchronized void doC(){
        //C 监视器 Sync.class
    }

    public   void doD(){
        synchronized (Sync.class){
            //D 监视器 Sync.class
        }
    }

}
