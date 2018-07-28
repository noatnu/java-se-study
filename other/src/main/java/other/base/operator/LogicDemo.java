package other.base.operator;

import help.Zhou_StdRandom;
import util.log.LoggerFactoryGET;

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
        logger.info("原始数据i:"+i);
        logger.info("原始数据j:"+i);

        logger.info(String.format("i > j---boolean: %b",(i > j)));
    }

    public static void main(String[] args) {
        new LogicDemo().boolA();
    }
}
