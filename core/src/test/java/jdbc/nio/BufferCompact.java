package jdbc.nio;

import java.nio.ByteBuffer;

/**
 */
public class BufferCompact {
    /**
     * @see 压缩此缓冲区
     */
    public static void isCompact() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 'H').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');
        System.out.println(buffer.toString());

        //取2个出来
//        buffer.flip();
        buffer.limit(buffer.position()).position(0);//准备  开始取
        System.out.println(buffer.toString());

        for (int i = 0; i < 2; i++) {
            if (buffer.hasRemaining()) System.out.println("get()" + (char) buffer.get());
        }

        System.out.println(buffer.toString());


        //假如还需要继续存,但是此缓冲区的前面两个已经变为了空字节了
        //这时compact()复制后面的字节并且把缓冲器变为了可以继续填充
        buffer.compact();

        System.out.println(buffer.toString());
        //...............继续填充
    }

    public static void main(String[] args) {
        isCompact();
    }
}
