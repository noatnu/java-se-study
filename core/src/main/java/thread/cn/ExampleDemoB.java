package thread.cn;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tools.ant.util.DateUtils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/7/3
 **/
public class ExampleDemoB {

    public void testA(){
        new Timer(RandomStringUtils.random(88)).schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("bombing ====================== || =====================");
            }
        }, 1000);
        while (true){
            System.out.println(DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void testB(){
        new Timer(RandomStringUtils.random(88)).schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("bombing ====================== || =====================");
            }
        }, 1000,3000);
    }


    public static void main(String[] args) {
//       new ExampleDemoB().testB();
       new ExampleDemoB().testA();
    }


}
