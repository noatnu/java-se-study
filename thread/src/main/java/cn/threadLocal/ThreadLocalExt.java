package cn.threadLocal;

public class ThreadLocalExt extends ThreadLocal {
    @Override
    protected Object initialValue() {
        return "ThreadLocal init value is not null";
    }
}
// ThreadLocal 初始化不再为null