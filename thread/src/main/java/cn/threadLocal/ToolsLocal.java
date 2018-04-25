package cn.threadLocal;

import java.util.Date;

public class ToolsLocal {
    public static ThreadLocal<Object> t1 = new ThreadLocal<>();
    public static ThreadLocal<Date> t2 = new ThreadLocal<>();
}
