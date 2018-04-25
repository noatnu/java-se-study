package cn.threadcommunication.pipe.pepeInputOutput;

import java.io.IOException;
import java.io.PipedInputStream;

public class ReadData {
    public void readMethod(PipedInputStream input){
        try {
            System.out.println("read ...................");
            byte[] bytes = new byte[1024];
            int readLenth = input.read(bytes);
            while (readLenth!=-1){
                String data = new String(bytes,0,readLenth);
                System.out.println(data);
                readLenth = input.read(bytes);
            }
            System.out.println();
            input.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
