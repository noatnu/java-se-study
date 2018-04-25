package cn.threadcommunication.pipe.pepeInputOutput;

import java.io.PipedInputStream;

public class ThreadRead extends Thread{
    private PipedInputStream input;
    private ReadData readData;

    public ThreadRead(PipedInputStream input, ReadData readData) {
        this.input = input;
        this.readData = readData;
    }

    @Override
    public void run() {
        readData.readMethod(input);
    }
}
