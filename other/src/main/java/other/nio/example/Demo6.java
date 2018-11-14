package other.nio.example;


import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.Random;

public class Demo6 {
    /*Buffer的clear()学习*/
    @Test
    public  void isClear(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 'z').put((byte)'h').put((byte)'o').put((byte)'u');
        System.out.println("remaining "+buffer.remaining());
        System.out.println("position and limit:"+buffer);

        buffer.clear();//作用 position=0 limit=capacity
        System.out.println(buffer+" "+buffer.remaining());
        //ps 虽然position 被设置为了0,但是缓冲区中的数据并没有消失,当然你可以向里面填充数据
        buffer.put((byte)new Random(47).nextInt(20));
        System.out.println(buffer);//又可以继续被填充数据了,其实1处是有数据的但是被新填进来的数据覆盖了
    }
}
