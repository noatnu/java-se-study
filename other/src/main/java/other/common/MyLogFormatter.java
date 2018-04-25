package other.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MyLogFormatter extends Formatter {

    // Create a DateFormat to format the logger timestamp.
    private static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS");

    @Override
    public String getHead(Handler h) {
        return super.getHead(h);
    }

    @Override
    public String getTail(Handler h) {
        return super.getTail(h);
    }

    @Override
    public synchronized String formatMessage(LogRecord record) {
        return super.formatMessage(record);
    }

    @Override
    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder(1000);
        builder.append(df.format(new Date(record.getMillis()))).append(" - ");
        builder.append("[").append(record.getSourceClassName()).append(".");
        builder.append(record.getSourceMethodName()).append("] - \n");
        builder.append("\t[").append(record.getLevel()).append("] - ");
        builder.append(formatMessage(record));
        builder.append("\n\n");
        return builder.toString();
    }
}
