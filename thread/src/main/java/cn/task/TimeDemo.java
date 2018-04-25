package cn.task;

import org.testng.annotations.Test;

import java.util.Timer;
import java.util.logging.Logger;

/**
 * Created by zhou on 18-1-30.
 */
public class TimeDemo {
    private Logger logger = Logger.getLogger(""+this);

    @Test
    public void isTask()throws Exception{
        Timer timer = new Timer("timer");
        timer.schedule(new TimerTaskDemo(),3000);
        Thread.sleep(3000);
        logger.info("isTask()");
    }

    @Test
    public void task(){
        while (true){
            Timer timer = new Timer("timer");
            timer.schedule(new TimerTaskDemo(),1000);
        }
    }

}
