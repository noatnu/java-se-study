package tool.log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Auther: zch
 * @Date: 2018/7/28 17:07
 * @Description:
 */
public class LoggerFactoryGET {
    public final static Logger loggerALL = LogUtilA.setLoggerHanlder(Level.ALL);
    private static LoggerFactoryGET loggerFactory = new LoggerFactoryGET();

    public LoggerFactoryGET() {
    }

    public static LoggerFactoryGET getLoggerFactory() {
        return loggerFactory;
    }

    public Logger getLoggerAll() {
        return LogUtilA.setLoggerHanlder(Level.ALL);
    }
}
