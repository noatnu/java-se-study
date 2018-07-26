package other.nio.example;

import org.testng.annotations.Test;
import util.log.LogUtilA;

import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo2 {
    private final Logger loggerA = LogUtilA.setLoggerHanlder(Level.ALL);

    @Test
    public void compact(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        byteBuffer.put((byte)'h').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'o');

        byteBuffer.put(0+0,(byte)'M').put((byte)'w');//第一个字母相当于修改第二个字母相当于添加
        loggerA.info(print(byteBuffer));

        byteBuffer.limit(byteBuffer.position()).position(0+0);
        byteBuffer.get();byteBuffer.get();//释放两个元素
        //压缩
        byteBuffer.compact();
        loggerA.info(print(byteBuffer));
        /**
         信息: 返回此缓冲区的容量:10 返回此缓冲区的限制:10 返回此缓冲区的位置:6
         信息: 返回此缓冲区的容量:10 返回此缓冲区的限制:10 返回此缓冲区的位置:4
         */
        byteBuffer.put((byte)'x').put((byte)'X');
        byteBuffer.flip();//byteBuffer.limit(byteBuffer.position()).position(0);
        byte[] bs = new byte[byteBuffer.remaining()];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = byteBuffer.get();
        }
        loggerA.info("data:"+new String(bs));//llowxX
        /**
         * 信息: data:llowxX
         */
    }

    //压缩前
    /**
     * 0   1   2   3   4   5   6   7   8   9   10
     ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬─       已经释放两个元素之后
     │ M │ e │ l │ l │ o │ w │   │   │   │   │   │
     │───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴─
       ⇡       ⇡               ⇡               ⇡
       │       │               │               │
     ┌───┐    ┌───┐          ┌───┐           ┌───┐
     │ x │    │ 2 │          │ 6 │           │ 10│
     └───┘    └───┘          └───┘           └───┘
     mark     position        limit          capacity
     */

    //压缩后
    /**
     * 0   1   2   3   4   5   6   7   8   9   10
     ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬⇙
     │ l │ l │ o │ w │ o │ w │   │   │   │   │   │─────────────┬
     ├───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴⇖            │
     ⇡                         ⇡               ⇡               │
     ⇡              ───────────⇡               ⇡               │
     │             │                           │               │
     ┌───┐       ┌─┴─┐                       ┌─┴─┐           ┌───┐
     │ x │       │ 2 │                       │ 10│           │ 10│
     └───┘       └───┘                       └───┘           └───┘
     mark        position                     limit          capacity
     数据元素 2-5 被复制到 0-3 位置。位置 4 和 5 不受影响
     */

    public String print(ByteBuffer byteBuffer){
        StringBuilder builder = new StringBuilder(1024);
        builder.append("返回此缓冲区的容量:"+byteBuffer.capacity()+" 返回此缓冲区的限制:"+byteBuffer.limit());
        builder.append(" 返回此缓冲区的位置:"+byteBuffer.position());
        return builder.toString();
    }
}
