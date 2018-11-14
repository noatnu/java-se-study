package other.base.operator;

import tool.help.Zhou_StdRandom;
import tool.log.LoggerFactoryGET;

import java.util.Random;
import java.util.logging.Logger;

/**
 * @Auther: zch
 * @Date: 2018/7/28 09:49
 * @Description:逻辑操作符
 */
public class LogicDemo {
    private final Logger logger = LoggerFactoryGET.loggerALL;

    public void boolA(){
        int i = Zhou_StdRandom.uniform(100);
        int j = Zhou_StdRandom.uniform(101);
        logger.info(String.format("原始数据i: %d",i));
        logger.info(String.format("原始数据j %d",j));

        logger.info(String.format("i > j---boolean: %b",(i > j)));

        int a = new Random().nextInt(10);
        int b = new Random().nextInt(10);
        logger.info(String.format("原始数据a= %d",a));
        logger.info(String.format("原始数据b= %d",b));

        logger.info(String.format("a==b 结果: %b",(a==b)));
        logger.info(String.format("a>=b 结果: %b",(a>=b)));
        logger.info(String.format("a!=b 结果: %b",(a>=b)));
        logger.info(String.format("a<=b 结果: %b",(a<=b)));
        logger.info(String.format("a<b 结果: %b",(a<b)));
    }

    public static void main(String[] args) {
        new LogicDemo().boolA();
    }
}
