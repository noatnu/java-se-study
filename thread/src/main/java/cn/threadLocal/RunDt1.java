package cn.threadLocal;

public class RunDt1 {
    public static ThreadLocal<String> t1 = new ThreadLocal<>();

    public static void main(String[] args) {
        if (t1.get() == null) {
            System.out.println("thread local value is null!");
            t1.set("me and me");
        }
        System.out.println(t1.get());
        System.out.println(t1.get());
    }
}
