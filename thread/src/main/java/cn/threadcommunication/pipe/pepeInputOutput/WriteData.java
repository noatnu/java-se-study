package cn.threadcommunication.pipe.pepeInputOutput;

import java.io.IOException;
import java.io.PipedOutputStream;

public class WriteData {
    public void writeMethod(PipedOutputStream out){
        try {
            System.out.println("write ..................");
            for (int i = 0; i < 300; i++) {
                String data = ""+(i++);
                out.write(data.getBytes("UTF-8"));
                System.out.println(data);
            }
        }catch (IOException e){
            System.out.println(""+e.getLocalizedMessage());
        }
    }
}
