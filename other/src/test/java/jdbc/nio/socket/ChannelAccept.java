package jdbc.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by zhou on 17-12-26.
 */
public class ChannelAccept {
    public static final String GREETING = "Hello I must be going.\r\n";

    public static void main(String[] args)throws IOException ,InterruptedException{
        init();
    }

    public static void init()throws IOException,InterruptedException{
        int port = 1234; // default
        ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes());
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);
        while (true){
            System.out.println ("Waiting for connections");
            SocketChannel sc = ssc.accept();
            if (sc==null){
                Thread.sleep(2000);
                System.out.println(sc);
            }else {
                System.out.println("Incoming connection from: " +sc.socket().getRemoteSocketAddress());
                buffer.rewind();
                sc.write(buffer);
                sc.close();
            }
        }
    }
}
