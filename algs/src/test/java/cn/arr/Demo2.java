package cn.arr;

import org.testng.annotations.Test;
import util.array.ToObjectArray;
import util.log.LoggerFactoryGET;

import java.util.Arrays;
import java.util.logging.Logger;

public class Demo2 {
    private final Logger logger = LoggerFactoryGET.getLoggerFactory().getLoggerAll();

    @Test
    public void testArr(){
        logger.info(Arrays.toString(ToObjectArray.getToObjectArray().getUsers()));
    }
}
