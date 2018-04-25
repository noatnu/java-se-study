package jdbc.nio.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by zhou on 17-12-24.
 * 通道使用
 */
public class ChannelCopy {
    public static void main(String[] args)throws IOException {
        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);
        System.out.println("start!");
        channelCopy2(source,dest);
        source.close();
        dest.close();
        System.out.println("end!");
    }

    private final static void channelCopy2(ReadableByteChannel src, WritableByteChannel dest)throws IOException{
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024*16);
        while ((src.read(buffer))!=-1){
            buffer.flip();
            dest.write(buffer);
            while (buffer.hasRemaining()){
                dest.write(buffer);
            }
            buffer.clear();//准备下一次填充数据 Make the buffer empty, ready for filling
        }
    }

    private final static void channelcopy1(ReadableByteChannel src, WritableByteChannel dest)throws IOException{
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024*16);
        while ((src.read(buffer))!=-1){//将字节序列从此通道中读入给定的缓冲区。 将src所在的通道读入到buffer缓冲区中
            // Prepare the buffer to be drained 缓冲区准备释翻转
            buffer.flip();
            dest.write(buffer);//将字节序列从给定的缓冲区中写入此通道 将buffer缓冲区写入 dest所在的通道

            //If partial transfer, shift remainder down 如果还有剩余的元素,那么向下转移
            // If buffer is empty, same as doing clear 如果缓冲区为null 那么与clear()差不多哦
            //压缩
            buffer.compact();//压缩
        }

        // EOF will leave buffer in fill state
        //如果缓冲区还有数据  (ps漏网之鱼)
        buffer.flip();
        while (buffer.hasRemaining()){
            dest.write(buffer);
        }
    }
}
