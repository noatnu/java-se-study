package other.nio.buffer;


import org.junit.jupiter.api.Test;
import tool.log.LogUtilA;

import java.nio.Buffer;
import java.nio.CharBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateBuffer {
    private final Logger loggerA = LogUtilA.setLoggerHanlder(Level.ALL);

    @Test
    public void createOneA() {
        //第一种创建方式
        CharBuffer charBuffer = CharBuffer.allocate(16);//创建一个非直接缓冲区
        loggerA.info("one:" + print(charBuffer));
        /*console:缓冲区的容量:16 缓冲区的限制:16 缓冲区的位置:0*/

        //第二种方式
        char[] chars = new char[1024];
        charBuffer = CharBuffer.wrap(chars);
        loggerA.info("two:" + print(charBuffer));
        /**
         * 这段代码构造了一个新的缓冲区对象，但数据元素会存在于数组中。这意味着通过调用put()函数造成的对缓冲区的改动
         * 会直接影响这个数组，而且对这个数组的任何改动也会对这个缓冲区对象可见。
         * console:缓冲区的容量:1024 缓冲区的限制:1024 缓冲区的位置:0
         */

        //第二种方式的延伸
        charBuffer = CharBuffer.wrap(chars, 0, chars.length / 4);
        loggerA.info("three:" + print(charBuffer));
        /**
         * 这个函数并不像您可能认为的那样，创建了一个只占用了一个数组子集的缓冲区。这个缓
         冲区可以存取这个数组的全部范围； offset 和 length 参数只是设置了初始的状态。
         * console:缓冲区的容量:1024 缓冲区的限制:256 缓冲区的位置:0
         */
    }

    @Test
    public void equalsHasArray() {
        char[] chars = new char[1024];
        CharBuffer charBuffer = CharBuffer.wrap(chars, 0, chars.length / 4);
        boolean flag = charBuffer.hasArray();
        loggerA.info("flag:" + flag);
        /**
         * 如果 hasArray()函数返回 false，不要调用 array()函数或者 arrayOffset()函
         数。如果您这样做了您会得到一个 UnsupportedOperationException 异常
         */
    }

    public String print(Buffer buffer) {
        StringBuilder builder = new StringBuilder(1024);
        builder.append("缓冲区的容量:" + buffer.capacity() + " 缓冲区的限制:" + buffer.limit());
        builder.append(" 缓冲区的位置:" + buffer.position());
        return builder.toString();
    }

}
