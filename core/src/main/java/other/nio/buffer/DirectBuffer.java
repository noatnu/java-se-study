package other.nio.buffer;


import org.junit.jupiter.api.Test;
import tool.log.LogUtilA;

import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DirectBuffer {

    private final Logger loggerA = LogUtilA.setLoggerHanlder(Level.ALL);

    /*直接缓冲区被用于与通道和固有 I/O 例程交
互。它们通过使用固有代码来告知操作系统直接释放或填充内存区域，对用于通道直接或原始
存取的内存区域中的字节元素的存储尽了最大的努力。
直接缓冲区时 I/O 的最佳选择，但可能比创建非直接缓冲区要花费更高的成本。直接缓
冲区使用的内存是通过调用本地操作系统方面的代码分配的，绕过了标准 JVM 堆栈。建立和
销毁直接缓冲区会明显比具有堆栈的缓冲区更加破费，这取决于主操作系统以及 JVM 实现。*/

    //非直接缓冲不可能成为一个本地 I/O 操作的目标
    //向一个通道中传递一个非直接 ByteBuffer对象用于写入，通道可能会在每次调用中隐含地进行下面的操作：
    /*
        1.创建一个临时的直接 ByteBuffer 对象。
        2.将非直接缓冲区的内容复制到临时缓冲中。
        3.使用临时缓冲区执行低层次 I/O 操作。
        4.临时缓冲区对象离开作用域，并最终成为被回收的无用数据
    */

    /**
     * 直接 ByteBuffer 是通过调用具有所需容量的 ByteBuffer.allocateDirect()函数
     * 产生的，就像我们之前所涉及的 allocate()函数一样。注意用一个 wrap()函数所创建的被
     * 包装的缓冲区总是非直接的。
     */

    @Test
    public void direct() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        //isDirect() 检测是否是直接缓冲区
        loggerA.info(byteBuffer + "");
        loggerA.info("" + byteBuffer.isDirect());
        //1 最终JVM与本地线程交互的是直接缓冲区,并且直接缓冲区性能高于非直接缓冲区
        //2 直接缓冲区创建与销毁更加耗费
        //3 非直接缓冲区创建与销毁更加容易
    }
}
