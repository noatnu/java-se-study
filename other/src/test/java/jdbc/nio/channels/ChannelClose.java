package jdbc.nio.channels;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by zhou on 17-12-24.
 * 检测通道是否开启以及通道关闭
 */
public class ChannelClose {
    public static void main(String[] args)throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("BufferDemoWriteA_isWrite.txt","rw");
        FileChannel channel = accessFile.getChannel();
        System.out.println("channel is close "+channel.isOpen());

        channel.close();

        System.out.println("channel is close "+channel.isOpen());
    }
}
