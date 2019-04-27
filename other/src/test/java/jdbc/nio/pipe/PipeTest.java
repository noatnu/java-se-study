package jdbc.nio.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Random;

/**
 * Created by zhou on 17-12-27.
 */
public class PipeTest {
    private static String[] products = {
            "No good deed goes unpunished",
            "To be, or what?",
            "No matter where you go, there you are",
            "Just say \"Yo\"",
            "My karma ran over my dogma"
    };

    public static void main(String[] args) throws IOException, Exception {
        WritableByteChannel writableByteChannel = Channels.newChannel(System.out);
        ReadableByteChannel readableByteChannel = startWorker(10);
        ByteBuffer buffer = ByteBuffer.allocate(100);
        while (readableByteChannel.read(buffer) >= 0) {
            buffer.flip();
            writableByteChannel.write(buffer);
            buffer.clear();
        }
    }

    private static ReadableByteChannel startWorker(int reps)
            throws Exception {
        Pipe pipe = Pipe.open();
        Worker worker = new Worker(pipe.sink(), reps);
        worker.start();
        return (pipe.source());
    }

    private static class Worker extends Thread {
        WritableByteChannel channel;
        private int reps;
        private Random rand = new Random();

        Worker(WritableByteChannel channel, int reps) {
            this.channel = channel;
            this.reps = reps;
        }

        @Override
        public void run() {
            ByteBuffer buffer = ByteBuffer.allocate(100);
            try {
                for (int i = 0; i < this.reps; i++) {
                    doSomeWork(buffer);
                    // channel may not take it all at once
                    while (channel.write(buffer) > 0) {
                        // empty
                    }
                }
                this.channel.close();
            } catch (Exception e) {
                // easy way out; this is demo code
                e.printStackTrace();
            }
        }

        private void doSomeWork(ByteBuffer buffer) {
            int product = rand.nextInt(products.length);
            buffer.clear();
            buffer.put(products[product].getBytes());
            buffer.put("\r\n".getBytes());
            buffer.flip();
        }
    }
}
