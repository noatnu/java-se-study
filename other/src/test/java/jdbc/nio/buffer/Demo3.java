package jdbc.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * Created by zhou on 17-12-23.
 */
public class Demo3 {
    public static void main(String[] args) {
        isit();
    }

    /**
     * 缓冲器创建  大多数的情况下都是创建的非直接缓冲区(97%的情况下都不要过早的优化)
     */
    public final static void isit() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(22);//创建了一个capacity为22的直接缓冲区

        char[] chars = new char[100];
        CharBuffer charBuffer = CharBuffer.wrap(chars, 12, 42),
                charBuffer1 = CharBuffer.wrap(chars);
        //创建了一个position=12 capacity=chars.length() limit=42+12的非直接缓冲区
        // 创建了一个以chars数组为存储容器的缓冲器
        /*间接的缓冲区使用备份数组,像我们之前讨论的,您可以通过上面列出的
         * API 函数获得对这些数组的存取权。Boolean 型函数 hasArray()告诉您这个缓冲区是否有一个可存取的备份数组。
         * 如果这个函数的返回 true,array()函数会返回这个缓冲区对象所
         * 使用的数组存储空间的引用。
         * */
        System.out.println(charBuffer.hasArray() ? "yes" : "no");//yes
    }

    public final static void isCreate() {
        //创建了一个大字节存储顺序
        ByteBuffer buffer = ByteBuffer.allocate(20).order(ByteOrder.BIG_ENDIAN);
    }
}
