package other.common;

import java.util.logging.Logger;

public class LogUtil {
    public static Logger getLogger(){
        System.setProperty("log.file","mylog.properties");
        Logger logger = Logger.getLogger("myLogger");
        return logger;
    }
}
