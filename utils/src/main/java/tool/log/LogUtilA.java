package tool.log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Auther: zch
 * @Date: 2018/7/26 20:48
 * @Description:
 */
public class LogUtilA {
    private static Calendar now = Calendar.getInstance();

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static final int year = now.get(Calendar.YEAR);

    private static final int month = now.get(Calendar.MONTH) + 1;

    private static final String LOG_FOLDER_NAME = "AIOClient";

    private static final String LOG_FILE_SUFFIX = "_LOG.log";

    private static Logger logger = Logger.getLogger("MyLogger_LOG");

    //使用唯一的fileHandler，保证当天的所有日志写在同一个文件里
    private static FileHandler fileHandler = getFileHandler();

    private static MyLogFormatter myLogFormatter = new MyLogFormatter();

    private static FileHandler getFileHandler() {
        FileHandler fileHandler = null;
        boolean APPEND_MODE = true;
        try {
            //文件日志内容标记为可追加
            fileHandler = new FileHandler(getLogFilePath(), APPEND_MODE);
            return fileHandler;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private synchronized static String getLogFilePath() {
        StringBuffer logFilePath = new StringBuffer();
//        logFilePath.append(System.getProperty("user.home"));
        logFilePath.append("log\\");
        logFilePath.append(File.separatorChar);
        logFilePath.append(LOG_FOLDER_NAME);
        logFilePath.append(File.separatorChar);
        logFilePath.append(year);
        logFilePath.append(File.separatorChar);
        logFilePath.append(month);

        File dir = new File(logFilePath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        logFilePath.append(File.separatorChar);
        logFilePath.append(sdf.format(new Date()));
        logFilePath.append(LOG_FILE_SUFFIX);

//        System.out.println(logFilePath.toString());
        return logFilePath.toString();
    }

    public synchronized static Logger setLoggerHanlder() {
        return setLoggerHanlder(Level.ALL);
    }

    //    SEVERE > WARNING > INFO > CONFIG > FINE > FINER > FINESET
    public synchronized static Logger setLoggerHanlder(Level level) {

        try {
            //以文本的形式输出
//            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setFormatter(myLogFormatter);

            logger.addHandler(fileHandler);
            logger.setLevel(level);
        } catch (SecurityException e) {
            logger.severe(populateExceptionStackTrace(e));
        }
        return logger;
    }
    private synchronized static String populateExceptionStackTrace(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.toString()).append("\n");
        for (StackTraceElement elem : e.getStackTrace()) {
            sb.append("\tat ").append(elem).append("\n");
        }
        return sb.toString();
    }
}
