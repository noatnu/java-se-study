package Interview.a2;

import java.util.logging.Logger;

public class ForTest {
    private static final Logger logger = Logger.getLogger(Object.class.toString());

    public static void main(String[] args) {
        isC();
    }

    public static void isC() {
        int i = 0;
        for (; i < 8; i++) {
            logger.info("time:" + System.currentTimeMillis());
            logger.info("system:" + System.getProperty("user.dir"));
            logger.info("system:" + System.getProperty("java.vm.name"));
            logger.info("system:" + System.getProperty("java.home"));
            logger.info("system:" + System.getProperty("java.version"));
        }
    }

    public static void isB() {
        /*
        for (;1>6;){//for循环 开始计算的表达式必须为 true
            logger.info("math:"+ (Math.round(Math.random())*10));
        }
        */
        for (; 1 < 6; ) {//for循环 开始计算的表达式必须为 true
            logger.info("math:" + (Math.round(Math.random()) * 10));
        }
    }

    public static void isA() {
        for (; ; ) {
            logger.info("math:" + (Math.round(Math.random()) * 10));
        }
    }
}
