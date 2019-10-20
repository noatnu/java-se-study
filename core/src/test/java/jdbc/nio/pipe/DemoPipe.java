package jdbc.nio.pipe;


import tool.help.Zhou_String;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.channels.WritableByteChannel;

/**
 * Created by zhou on 17-12-26.
 */
public class DemoPipe {
    private final static Integer NUM = 1024 * 1000;

    public static void main(String[] args) throws IOException, Exception {
//        isit();
        isRun();
    }

    public final static void isit() throws IOException {
        /*创建管道通过Pipe.open()方法打开管道。*/
        Pipe pipe = Pipe.open();
        /*要向管道写数据，需要访问sink通道。像这样：*/
        Pipe.SinkChannel sinkChannel = pipe.sink();

        String newData = "New String to write to file..." + isGetData().toString();
        ByteBuffer buffer = ByteBuffer.allocate(NUM);//创建缓冲器
        buffer.put(newData.getBytes("UTF-8"));
        buffer.flip();
        System.out.println("--> " + buffer);
        while (buffer.hasRemaining()) {
            sinkChannel.write(buffer);
        }

        /*从管道读取数据*/
        /*从读取管道的数据，需要访问source通道，像这样：*/
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer byteBuffer = ByteBuffer.allocate(NUM);
        StringBuilder builder = new StringBuilder(1024);
        int i = sourceChannel.read(byteBuffer);
        byteBuffer.flip();
        System.out.println(i + " --<" + byteBuffer);
        byte[] bs = new byte[byteBuffer.remaining()];
        int j = 0;
        while (byteBuffer.hasRemaining()) {
            bs[j++] = byteBuffer.get();
        }
        System.out.println(new String(bs, "UTF-8"));
        System.out.println(byteBuffer);

    }

    public static void isRun() throws Exception {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String newData = isGetData().toString();
        ByteBuffer buffer = ByteBuffer.allocate(NUM);//创建缓冲器
        buffer.put(newData.getBytes("UTF-8"));
        buffer.flip();
        while (buffer.hasRemaining()) {
            sinkChannel.write(buffer);
        }

        Pipe.SourceChannel sourceChannel = pipe.source();
        WritableByteChannel writableByteChannel = Channels.newChannel(System.out);
        ByteBuffer byteBuffer = ByteBuffer.allocate(NUM);
        sourceChannel.read(byteBuffer);
        byteBuffer.flip();
        writableByteChannel.write(byteBuffer);
    }

    public static StringBuilder isGetData() {
        StringBuilder builder = new StringBuilder(1024);
        for (int i = 0; i < 1000; i++) {
            builder.append(Zhou_String.toLowerCase(3) + " ");
            if (i % 10 == 0) builder.append("\n");
        }
        return builder;
    }
}
