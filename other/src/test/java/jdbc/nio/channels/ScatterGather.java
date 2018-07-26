package jdbc.nio.channels;


import help.Zhou_Word;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * Created by zhou on 17-12-24.
 */
public class ScatterGather {
    private final static String st = "ScatterGather.txt";
    public static void main(String[] args)throws IOException {
        isGatheringWrite();
        isScatter();
    }

    public final static void isScatter()throws IOException{
        ByteBuffer head = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        //插入到Buffer数组
        ByteBuffer[] array = new ByteBuffer[]{head,body};

        FileInputStream in = new FileInputStream(DocDemo.path+""+st);
        ScatteringByteChannel byteChannel = in.getChannel();

        //注意buffer首先被插入到数组，然后再将数组作为channel.read() 的输入参数。
        byteChannel.read(array);//多个缓冲区读入一个通道(ps:byteChannel)
        /**
         * read()方法按照buffer在数组中的顺序将从channel中读取的数据写入到buffer，当一个buffer被写满后，channel紧接着向另一个buffer中写。
         * (也就是说head被填充完成以后才开始填充body)
         * Scattering Reads在移动下一个buffer前，必须填满当前的buffer，这也意味着它不适用于动态消息(译者注：消息大小不固定)。
         * 换句话说，如果存在消息头和消息体，消息头必须完成填充（例如 128byte），Scattering Reads才能正常工作
         */
        System.out.println(head+" "+body);
        if (head.position()>0){//说明有数据
            head.flip();//翻转
            byte[] bytes = new byte[head.remaining()];
            int i = 0;
            while (head.hasRemaining()){
                bytes[i++] = head.get();
            }
            System.out.println("head data:"+new String(bytes,"UTF-8"));
        }
        if (body.position()>0){
            body.flip();
            byte[] bytes = new byte[body.remaining()];
            int i = 0;
            while (body.hasRemaining()){
                bytes[i++] = body.get();
            }
            System.out.println("body data:"+new String(bytes,"UTF-8"));
        }

    }

    public final static void isGatheringWrite()throws IOException{
        ByteBuffer buffer1 = ByteBuffer.allocate(18);
        ByteBuffer buffer2 = ByteBuffer.allocate(18);
        /*-----------------------------填充数据----------------------------*/
        buffer1.put((Zhou_Word.getChineseName()+"---").getBytes("UTF-8"));
        buffer2.put((Zhou_Word.getChineseName()+"   ").getBytes("UTF-8"));
        //---------翻转
        buffer1.flip();
        buffer2.flip();
        //........................................插入到Buffer数组............................
        ByteBuffer[] array = new ByteBuffer[]{buffer1,buffer2};
        FileOutputStream output = new FileOutputStream(DocDemo.path+""+st,true);
        GatheringByteChannel byteChannel = output.getChannel();
        byteChannel.write(array);

        System.out.println("end!");
    }
}
