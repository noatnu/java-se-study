package jdbc.nio;

import java.nio.ByteBuffer;

/**
 * Created by zhou on 17-12-22.
 */
public class BufferMark {
    public static void main(String[] args) {
        isMark();
    }

    public static void isMark(){
        ByteBuffer buffer = ByteBuffer.allocate(20);

        buffer.position(2).mark();//mark index is 2
        System.out.println(buffer.toString());//[pos=2 lim=20 cap=20]
       buffer.put((byte) 'H');
        System.out.println(buffer.toString());//[pos=3 lim=20 cap=20]
        buffer.reset();//调用reset()之后又回到标记的位置了
        System.out.println(buffer.toString());//[pos=2 lim=20 cap=20]
    }
}
