package jdbc.nio;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by zhou on 17-12-22.
 */
public class BufferDemo {

    public final static void isBuffer()throws IOException{

        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.limit(9);//人为设定9
        System.out.println("返回此缓冲区的位置。"+buffer.position());
        System.out.println("返回此缓冲区的限制。"+buffer.limit());
        //Hello  开始的时候limit和capacity在同一位置(本例中10)
        System.out.println("put 添加数据");
        buffer.put((byte)'H').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'o');
        System.out.println(buffer.toString());
        System.out.println("返回此缓冲区的位置。"+buffer.position());
        System.out.println("返回此缓冲区的限制。"+buffer.limit());
        //当填充数据之后position位置逐渐的向后递增

        //既然我们已经在 buffer 中存放了一些数据,如果我们想在不丢失位置的情况下进行一些
        //更改该怎么办呢?put()的绝对方案可以达到这样的目的。假设我们想将缓冲区中的内“Hello”的 ASCII 码更改为“Mellow”。
        // 我们可以这样实现:
        System.out.println("修改数据!");
        buffer.put(0,(byte)'M').put((byte)'W');

        /**
         * 我们已经写满了缓冲区,现在我们必须准备将其清空。我们想把这个缓冲区传递给一个通
         道,以使内容能被全部写出。但如果通道现在在缓冲区上执行 get(),那么它将从我们刚刚
         插入的有用数据之外取出未定义数据。如果我们将位置值重新设为 0,通道就会从正确位置开
         始获取,但是它是怎样知道何时到达我们所插入数据末端的呢?这就是上界属性被引入的目
         的。上界属性指明了缓冲区有效内容的末端。我们需要将上界属性设置为当前位置,然后将位
         置重置为 0。我们可以人工用下面的代码实现:
         */
//        buffer.limit(buffer.position()).position(0);

        /**
         * 但这种从填充到释放状态的缓冲区翻转是 API 设计者预先设计好的,他们为我们提供一个非常便利的函数:
         */
//        buffer.flip();//效果和上面一致

        int xxB = buffer.position();
        buffer.rewind();//和flip()效果差不多,但是不影响界限或者说是上界,因此最好是先设置上界
        buffer.limit(xxB);

        System.out.println("-->"+buffer.toString());//限制变为了6,但是位置变为了0(位置必须为0才可以get或者通道读取)
        System.out.println("剩余元素数量!"+buffer.remaining());
        int length = buffer.limit()-1;
        byte[] bs = new byte[length];
        for (int i = 0; i<length; i++) {
            if (buffer.hasRemaining()) bs[i] = buffer.get();
        }
        System.out.println("剩余元素数量!"+buffer.remaining());
        System.out.println("<--"+buffer.toString());
        System.out.println("get() data :"+new String(bs));

    }

    public static void main(String[] args)throws IOException {
        isBuffer();
    }
}
