package other.nio.example;

import org.junit.jupiter.api.Test;
import tool.log.LogUtilA;

import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo1 {
    private final Logger loggerA = LogUtilA.setLoggerHanlder(Level.ALL);

   /*
   * 未填充前
   * 0   1   2   3   4   5   6   7   8   9   10
     ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬⇙
     │   │   │   │   │   │   │   │   │   │   │   │─────────────┬
     ├───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴⇖            │
     ⇡                                         ⇡               │
     ⇡─────────────│                           │
     │             │                           │               │
     ┌───┐       ┌─↓─┐                       ┌─┴─┐           ┌───┐
     │ x │       │ 0 │                       │ 10│           │ 10│
     └───┘       └───┘                       └───┘           └───┘
     mark        position                     limit          capacity

     //填充之后

     * 0   1   2   3   4   5   6   7   8   9   10
     ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬⇙
     │ h │ e │ l │ l │ o │ w │   │   │   │   │   │─────────────┬
     ├───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴⇖            │
     ⇡                         ⇡               ⇡               │
     ⇡             │───────────│               │
     │             │                           │               │
     ┌───┐       ┌─↓─┐                       ┌─┴─┐           ┌───┐
     │ x │       │ 6 │                       │ 10│           │ 10│
     └───┘       └───┘                       └───┘           └───┘
     mark        position                     limit          capacity

     翻转之后 byteBuffer.limit(byteBuffer.position()).position(0+0)或者 byteBuffer.flip()以前面为准

     * 0   1   2   3   4   5   6   7   8   9   10
     ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬
     │ h │ e │ l │ l │ o │ w │   │   │   │   │   │
     ├───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴──⇡─
     ⇡                          ⇡               │
     ⇡───────────               │               │
     │          │               │               │
     ┌───┐    ┌─↓─┐           ┌─┴─┐           ┌───┐
     │ x │    │ 0 │           │ 6 │           │ 10│
     └───┘    └───┘           └───┘           └───┘
     mark    position          limit          capacity

   * */

    public void get(ByteBuffer byteBuffer) {
        /**
         * 我们已经写满了缓冲区，现在我们必须准备将其清空。我们想把这个缓冲区传递给一个通
         道，以使内容能被全部写出。但如果通道现在在缓冲区上执行 get()，那么它将从我们刚刚
         插入的有用数据之外取出未定义数据。如果我们将位置值重新设为 0，通道就会从正确位置开
         始获取，但是它是怎样知道何时到达我们所插入数据末端的呢？这就是上界属性被引入的目
         的。上界属性指明了缓冲区有效内容的末端。我们需要将上界属性设置为当前位置，然后将位
         置重置为 0。我们可以人工用下面的代码实现：buffer.limit(buffer.position()).position(0);
         */
        byteBuffer.limit(byteBuffer.position()).position(0 + 2);//加上2的缘故是因为最前面的两个并没有被添加元素,put() byteBuffer.position(2);
        //当然也可以这样 Buffer.flip(); 但是要注意是否像目前的例子这样人为的把字节流缓冲处理了
//        byteBuffer.flip();//打印结果如:  Mellow 有null

        /**
         * Rewind()函数与 flip()相似，但不影响上界属性。它只是将位置值设回 0。您可以使
         用 rewind()后退，重读已经被翻转的缓冲区中的数据。
         */
        //释放
        byte[] bytes = new byte[byteBuffer.remaining()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = byteBuffer.get();
        }

        loggerA.info(new String(bytes));

    }

    @Test
    public void put() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        loggerA.info("" + print(byteBuffer));

        byteBuffer.position(2);
        loggerA.info("" + print(byteBuffer));

        byteBuffer.put((byte) 'h').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');
        loggerA.info("" + print(byteBuffer));

        /**
         * 既然我们已经在 buffer 中存放了一些数据，如果我们想在不丢失位置的情况下进行一些
         更改该怎么办呢？ put()的绝对方案可以达到这样的目的。假设我们想将缓冲区中的内容从
         “Hello”的 ASCII 码更改为“ Mellow”。我们可以这样实现：buffer.put(0,(byte)'M').put((byte)'w');
         */
        byteBuffer.put(0 + 2, (byte) 'M').put((byte) 'w');//第一个字母相当于修改第二个字母相当于添加

        get(byteBuffer);//释放元素
    }

    /**
     * Java NIO一共有四个参数
     * 0 1 2 3 4 5 6 7 8 9 10
     * - - - - - - - - - - - -
     * - - - - - - - - - - - -
     * mark                limit
     * position            capacity
     * 其中capacity表示容量(ps在创建的时候被设定之后就永远不能改变)
     * position表示位置 表示下一个要读或者写的索引.位置会自动由get()和put()更新
     * limit表示缓冲区 下一个不能读和写的索引
     * mark表示标记 设定时与position()方法连用   调用reset() method即可恢复mark标记的位置
     */


    //最重要的就是读的时候是从position到limit之间,写的时候同样是position到limit之间
    /*创建最初假如不显示的设定Limit 位置那么limit和capacity一致,
        并且如果不显示的设置position的位置那么position初始位置为0*/
    public String print(ByteBuffer byteBuffer) {
        StringBuilder builder = new StringBuilder(1024);
        builder.append("返回此缓冲区的容量:" + byteBuffer.capacity() + " 返回此缓冲区的限制:" + byteBuffer.limit());
        builder.append(" 返回此缓冲区的位置:" + byteBuffer.position());
        return builder.toString();
    }
}
