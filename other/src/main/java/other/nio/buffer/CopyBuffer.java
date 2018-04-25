package other.nio.buffer;

import org.testng.annotations.Test;
import other.common.LogUtilA;

import java.nio.Buffer;
import java.nio.CharBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CopyBuffer {
    private final Logger loggerA = LogUtilA.setLoggerHanlder(Level.ALL);

    @Test
    public void copyBufferA(){
        char[] chars = new char[1024];
        CharBuffer charBuffer = CharBuffer.wrap(chars,0,chars.length/4);
        charBuffer.put("A Hangzhou resident scans the QR code on a shared car.");
        loggerA.info("原始缓冲区:"+print(charBuffer));
        CharBuffer copyBuffer = charBuffer.duplicate();
        loggerA.info("Copy缓冲区:"+print(copyBuffer));

        /**
         * 两个缓冲区共享数据元素，拥有同样的容量，但每个缓冲区拥有各自的位置，上界和标记属性。对一个缓冲区内的数
         据元素所做的改变会反映在另外一个缓冲区上。这一副本缓冲区具有与原始缓冲区同样的数据
         视图。
         */
    }

    @Test
    public void asReadyOnly(){
        char[] chars = new char[1024];
        CharBuffer charBuffer = CharBuffer.wrap(chars,0,chars.length/4);
        charBuffer.put("A Hangzhou resident scans the QR code on a shared car.");
        loggerA.info("原始缓冲区:"+print(charBuffer));
        CharBuffer copyBuffer = charBuffer.asReadOnlyBuffer();
        loggerA.info("只读的缓冲区视图:"+print(copyBuffer));
    }

    public String print(Buffer buffer){
        StringBuilder builder = new StringBuilder(1024);
        builder.append("缓冲区的容量:"+ buffer.capacity()+" 缓冲区的限制:"+ buffer.limit());
        builder.append(" 缓冲区的位置:"+ buffer.position());
        return builder.toString();
    }
}
