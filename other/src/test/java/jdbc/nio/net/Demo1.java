package jdbc.nio.net;



import tool.help.Zhou_Word;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhou on 17-12-25.
 */
public class Demo1 {
    public final String IP = "172.16.121.159";
    public static void main(String[] args)throws IOException {
        Demo1 demo = new Demo1();
        demo.isRead();
    }

    /*Socket发送数据*/
    public final void isWrite()throws IOException{
        synchronized (this){
            Socket socket = new Socket(IP+"",8080);
            OutputStream output = socket.getOutputStream();
            output.write(Zhou_Word.getEnglishName().getBytes());
            output.flush();
            output.close();
            socket.close();
        }
    }

    public final void isRead()throws IOException{
        synchronized (this){
            Socket socket =new Socket(IP+"",8080);
            InputStream in = socket.getInputStream();
            int i ;
            while ((i=in.read())!=-1){
                System.out.println((char)i);
            }
            in.close();
            socket.close();
        }
    }

    public final void isit()throws IOException{
        ServerSocket serverSocket = new ServerSocket(8080);
        boolean flag = false;
        int i = 0;
        while (!flag){
            Socket clientSocket = serverSocket.accept();//对每个调用了accept()方法的类都只获得一个请求的连接。
            i++;
            if (i==10){clientSocket.close();break;}
        }
        serverSocket.close();
    }
}
