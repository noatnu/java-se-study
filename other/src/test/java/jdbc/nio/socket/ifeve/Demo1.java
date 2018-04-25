package jdbc.nio.socket.ifeve;


import zch.help.Zhou_String;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by zhou on 17-12-27.
 */
public class Demo1 {
    public static void main(String[] args) throws IOException{

    }

    public static void isCreate()throws IOException{
        /*Java NIO中的SocketChannel是一个连接到TCP网络套接字的通道。可以通过以下2种方式创建*/
        //1:打开一个SocketChannel并连接到互联网上的某台服务器。
        //2:一个新连接到达ServerSocketChannel时，会创建一个SocketChannel。
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8080));

        /*当用完SocketChannel之后调用SocketChannel.close()关闭SocketChannel*/
        socketChannel.close();

        //从 SocketChannel 读取数据
        //从SocketChannel中读取数据，调用一个read()的方法之一。
        ByteBuffer buffer = ByteBuffer.allocate(100);
        socketChannel.read(buffer);

        //写入 SocketChannel
        //写数据到SocketChannel用的是SocketChannel.write()方法，该方法以一个Buffer作为参数。示例如下
        ByteBuffer buffer1 = ByteBuffer.allocate(1024*18);
        StringBuilder builder = new StringBuilder(1024);
        for (int j =0;j<=100;j++)builder.append(Zhou_String.toUpperCase(4)+" ");
        buffer1.put(builder.toString().getBytes("UTF-8"));
        buffer1.flip();
        while (buffer1.hasRemaining())socketChannel.write(buffer1);
        /*注意SocketChannel.write()方法的调用是在一个while循环中的。Write()方法无法保证能写多少字节到SocketChannel。
        所以，我们重复调用write()直到Buffer没有要写的字节为止。*/

        //非阻塞模式
        /*可以设置 SocketChannel 为非阻塞模式（non-blocking mode）.
        设置之后，就可以在异步模式下调用connect(), read() 和write()了。*/
        socketChannel.configureBlocking(false);//非阻塞模式

        /*如果SocketChannel在非阻塞模式下，此时调用connect()，该方法可能在连接建立之前就返回了。
        为了确定连接是否建立，可以调用finishConnect()的方法。像这样*/
        while (!socketChannel.finishConnect()){ System.out.println("wait...........please"); }





        //write() 非阻塞模式下，write()方法在尚未写出任何内容时可能就返回了。所以需要在循环中调用write()。
        //read() 非阻塞模式下,read()方法在尚未读取到任何数据时可能就返回了。所以需要关注它的int返回值，它会告诉你读取了多少字节




    }
}
