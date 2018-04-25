package cn.task;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * Created by zhou on 18-1-30.
 */
public class TimerTaskDemo extends TimerTask {
    @Override
    public void run() {
        Logger logger = Logger.getLogger(this + "");
        DateFormat dateFormat = DateFormat.getDateInstance();
        logger.info("time:" + dateFormat.format(new Date()));
    }
}
