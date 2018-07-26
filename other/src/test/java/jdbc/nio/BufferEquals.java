package jdbc.nio;


import help.Zhou_String;

import java.nio.ByteBuffer;

/**
 * Created by zhou on 17-12-22.
 */
public class BufferEquals {
    public static void main(String[] args) {
        isEquals();
    }

    public static void isEquals(){
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        ByteBuffer buffer2 = ByteBuffer.allocate(10);
        buffer1.put((byte) Zhou_String.toLowerCase(2).charAt(0));
        buffer1.put((byte) Zhou_String.toLowerCase(2).charAt(0));
        if (buffer1.equals(buffer2)) System.out.println("yes");else System.out.println("no");;
        /**
          两个对象类型相同。包含不同数据类型的 buffer 永远不会相等,而且 buffer
         绝不会等于非 buffer 对象。
          两个对象都剩余同样数量的元素。Buffer 的容量不需要相同,而且缓冲区中剩
         余数据的索引也不必相同。但每个缓冲区中剩余元素的数目(从位置到上界)必须相
         同。
         
         在每个缓冲区中应被 Get()函数返回的剩余数据元素序列必须一致。

         */
    }
}
