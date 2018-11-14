package other.nio.buffer;


import org.junit.jupiter.api.Test;
import tool.log.LogUtilA;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewBuffer {
    private final Logger loggerA = LogUtilA.setLoggerHanlder(Level.ALL);
    /**
     * 视图缓冲区通过已存在的缓冲区对象实例的工厂方法来创建。这种视图对象维护它自己的
     属性，容量，位置，上界和标记，但是和原来的缓冲区共享数据元素。
     */

    @Test
    public void createViewBuffer(){
        //创建一个直接缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024).order(ByteOrder.BIG_ENDIAN);
        byteBuffer = ByteBuffer.allocate(1024).order(ByteOrder.BIG_ENDIAN);
        //创建一个视 图 缓 冲 区
        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        loggerA.info(""+print(byteBuffer));
        loggerA.info(""+print(charBuffer));
        //CharBuffer limit与capacity都减半
    }

    public String print(Buffer buffer){
        StringBuilder builder = new StringBuilder(1024);
        builder.append("缓冲区的容量:"+ buffer.capacity()+" 缓冲区的限制:"+ buffer.limit());
        builder.append(" 缓冲区的位置:"+ buffer.position());
        return builder.toString();
    }
}
