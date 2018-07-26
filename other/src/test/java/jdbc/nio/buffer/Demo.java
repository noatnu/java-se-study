package jdbc.nio.buffer;



import help.Zhou_Word;

import java.nio.ByteBuffer;

/**
 * Created by zhou on 17-12-23.
 */
public class Demo {
    public static void main(String[] args) {
        isit();
    }

    /**
     * 直接 ByteBuffer 是通过调用具有所需容量的 ByteBuffer.allocateDirect()函数
     产生的,就像我们之前所涉及的 allocate()函数一样。
     注意用一个 wrap()函数所创建的被包装的缓冲区总是非直接的。

     直接字节缓冲区通常是 I/O 操作最好的选择。在设计方面,它们支持 JVM 可用的最高效
     I/O 机制。非直接字节缓冲区可以被传递给通道,但是这样可能导致性能损耗。通常非直接缓
     冲不可能成为一个本地 I/O 操作的目标。如果您向一个通道中传递一个非直接 ByteBuffer
     对象用于写入,通道可能会在每次调用中隐含地进行下面的操作:
     1.创建一个临时的直接 ByteBuffer 对象。
     2.将非直接缓冲区的内容复制到临时缓冲中。
     3.使用临时缓冲区执行低层次 I/O 操作。
     4.临时缓冲区对象离开作用域,并最终成为被回收的无用数据。
     */


    public final static void isit(){
        //像这样叫做直接缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(22);

        //间接缓冲区或者说非直接缓冲区
        ByteBuffer buffer2 = ByteBuffer.allocate(21);

        System.out.println(buffer.isDirect()?"yes":"no");// 判断此字节缓冲区是否为直接的。

        //这样为非直接缓冲区
        ByteBuffer buffer1 = ByteBuffer.wrap(Zhou_Word.getEnglishName().getBytes());
        System.out.println(buffer1.isReadOnly()?"yes":"no");

        //这由wrap()函数调用的为非直接缓冲区,直接缓冲区是最好的选择
    }
}
