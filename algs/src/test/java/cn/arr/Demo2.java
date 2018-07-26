package cn.arr;

import common.Logger;
import org.testng.annotations.Test;
import util.array.ToObjectArray;

import java.util.Arrays;

public class Demo2 {
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void testArr(){
        logger.info(Arrays.toString(ToObjectArray.getToObjectArray().getUsers()));
    }
}
