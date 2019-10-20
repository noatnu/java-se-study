package other.nio.example;


import org.junit.jupiter.api.Test;
import tool.log.LogUtilA;

import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo5 {//批量移动

    private final Logger loggerA = LogUtilA.setLoggerHanlder(Level.ALL);

    @Test
    public void morePutMove() {//批量填充
        StringBuilder builder = new StringBuilder(1024 * 5);
        builder.append("Hainan province is taking a series of steps to help eradicate poverty by 2020, in line with the central government's poverty reduction plan.\n");
        byte[] bytes = builder.toString().getBytes();
        ByteBuffer buffer1 = ByteBuffer.allocate(0 + bytes.length);
        buffer1.put(bytes, 0, bytes.length);
        buffer1.flip();
        bytes = new byte[buffer1.remaining()];
        buffer1.get(bytes, 0, buffer1.remaining());
        loggerA.info("" + new String(bytes));
    }

    @Test
    public void moreGetMove() {//批量取出
        ByteBuffer buffer1 = ByteBuffer.allocate(10);

        buffer1.put((byte) 'h').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');

        byte[] array = new byte[1024];//大数组

        buffer1.flip();// buffer1.limit(buffer1.position()).position(0);

//        buffer1.get(array);//相当于 buffer1.get(array,0,array.length);

        /*要求整个数组被填充。如果缓冲区中的数据不够完全填满数组，您会得到一个异常。这意味着如果您想将一个小型缓冲区传入一个大型数组，
        需要明确地指定缓冲区中剩余的数据长度。*/

        buffer1.get(array, 0, buffer1.remaining());// 必须确定 从buffer批量取出的元素个数(buffer1.remaining())

        loggerA.info(new String(array));

    }

}
