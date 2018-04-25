package jdbc.nio.channels;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by zhou on 17-12-24.
 * 创建通道
 */
public class Demo {
    /**
     通道是访问 I/O 服务的导管。
     正如我们在第一章中所讨论的,I/O 可以分为广义的两大类别:
     File I/O 和 Stream I/O。
     那么相应地有两种类型的通道也就不足为怪了,它们是文件(file)通道和套接字(socket)通道。
     如果您参考一下图 3-2,您就会发现有一个 FileChannel 类和三个 socket 通
     道类:SocketChannel、ServerSocketChannel 和 DatagramChannel。
     */
    public static void main(String[] args)throws IOException {
        isit();
    }

    /*模拟创建通道*/
    public final static void isit()throws IOException{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("someHost",8080));
        System.out.println("SocketChannel channel"+socketChannel);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8098));
        System.out.println("ServerSocketChannel channel"+serverSocketChannel);

        DatagramChannel datagramChannel = DatagramChannel.open();
        System.out.println("DatagramChannel" + datagramChannel);

        RandomAccessFile accessFile = new RandomAccessFile("","rw");
        FileChannel channel = accessFile.getChannel();
        System.out.println("FileChannel "+channel);
    }
}
