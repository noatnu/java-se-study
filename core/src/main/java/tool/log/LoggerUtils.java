package tool.log;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.utils.DateUtils;

import java.util.Date;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/8/9
 **/
public class LoggerUtils {
    private final Logger log = LoggerFactory.getLogger(getClass()) ;
    /**
     * 自己拼接的异常打印数据
     * @param e
     */
    public void writeExceptionInfo(Exception e){
        writeExceptionInfo(e,null);
    }

    public void writeExceptionInfo(Exception e,String errorName){
        writeExceptionInfo(log,e,errorName) ;
    }

    /**
     * 编写一个日志书写模式
     * @param logger
     * @param e
     * @param errorName
     */
    public void writeExceptionInfo(Logger logger, Exception e, String errorName){
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append("{") ;
        stringBuilder.append("时间:").append(DateUtils.format(new Date(), DateUtils.DATETIME_PATTERN));
        stringBuilder.append(StringUtils.isNotBlank(errorName)?errorName:"").append("异常").append(",").append("异常具体原因");
        //默认jdk刚好第一个是调用方法  获取的是堆栈信息
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        if (stackTraceElement != null) {
            stringBuilder.append("[");
            stringBuilder.append("declaringClass:").append(stackTraceElement.getClassName());
            stringBuilder.append("methodName:").append(stackTraceElement.getMethodName());
            stringBuilder.append("fileName:").append(stackTraceElement.getFileName());
            stringBuilder.append("lineNumber:").append(stackTraceElement.getLineNumber());
            stringBuilder.append("message:").append(e.getMessage());
            stringBuilder.append("]");
        }
        stringBuilder.append("}") ;
        logger.debug(stringBuilder.toString());
        logger.error(stringBuilder.toString(), e);
    }

}
