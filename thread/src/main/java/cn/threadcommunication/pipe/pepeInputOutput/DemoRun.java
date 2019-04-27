package cn.threadcommunication.pipe.pepeInputOutput;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class DemoRun {
    public static void main(String[] args) {
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();

            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();

//            inputStream.connect(outputStream);
            outputStream.connect(inputStream);

            ThreadRead threadRead = new ThreadRead(inputStream, readData);
            threadRead.start();

            Thread.sleep(200);

            ThreadWrite threadWrite = new ThreadWrite(writeData, outputStream);
            threadWrite.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
