package other.nio.example;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

public class Demo4 {//两个缓冲区被认为相等

    @Test
    public void equalsTest() {
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        ByteBuffer buffer2 = ByteBuffer.allocate(10);
        buffer1.put((byte) 'h').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');
        buffer2.put((byte) 'I').put((byte) 'L').put((byte) 'o').put((byte) 'v').put((byte) 'e');//I LOVE
        //比较
        boolean flag = buffer1.equals(buffer2);
        System.out.println("flag:" + flag);

        /**
         * 两个对象类型相同。包含不同数据类型的 buffer 永远不会相等，而且 buffer绝不会等于非 buffer 对象。
         */

        /*
        两个对象都剩余同样数量的元素。 Buffer 的容量不需要相同，而且缓冲区中剩
        余数据的索引也不必相同。但每个缓冲区中剩余元素的数目（从位置到上界）必须相同。
         */

        /* 在每个缓冲区中应被 Get()函数返回的剩余数据元素序列必须一致*/
        /*如果不满足以上任意条件， 就会返回 false。*/
    }
}
