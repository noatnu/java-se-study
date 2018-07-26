package jdbc.nio.socket.ifeve;



import help.Zhou_String;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by zhou on 17-12-27.
 */
public class Demo1A {
    private final static int PORT = 8090;
    public static void main(String[] args)throws IOException {
        start(isData());
        end();
    }

    public static void start(ByteBuffer buffer)throws IOException{
        SocketChannel channelA = SocketChannel.open();
        channelA.connect(new InetSocketAddress(PORT));
        channelA.configureBlocking(false);
        buffer.flip();
        while (buffer.hasRemaining()){
            channelA.write(buffer);
        }
        System.out.println(channelA);
    }

    public static void end()throws IOException{
        SocketChannel channelA = SocketChannel.open();
        channelA.connect(new InetSocketAddress(PORT));
        channelA.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024*18);
        channelA.read(buffer);
        System.out.println(buffer);
    }

    public static ByteBuffer isData()throws UnsupportedEncodingException{
        ByteBuffer buffer1 = ByteBuffer.allocate(1024*18);
        StringBuilder builder = new StringBuilder(1024);
        for (int j =0;j<=100;j++)builder.append(Zhou_String.toUpperCase(4)+" ");
        buffer1.put(builder.toString().getBytes("UTF-8"));
        return buffer1;
    }
}
