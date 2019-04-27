package cn.arr;

import org.junit.jupiter.api.Test;
import tool.array.ToArray;
import tool.log.LoggerFactoryGET;

import java.util.Arrays;
import java.util.logging.Logger;

public class Demo1 {

    private final Logger logger = LoggerFactoryGET.getLoggerFactory().getLoggerAll();

    @Test
    public void testArr() {
        int[] arr = ToArray.getToArray().toIntArray();
        logger.info(Arrays.toString(arr));
    }
}
