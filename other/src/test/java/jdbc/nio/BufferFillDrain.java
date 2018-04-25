package jdbc.nio;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Created by zhou on 17-12-22.
 */
public class BufferFillDrain {
    private static int index = 0;
    private static String[] strings = {
            "A random string value",
            "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            "'Scuse me while I kiss this fly", // Sorry Jimi ;-)
            "Help Me! Help Me!",
    };

    public static void main(String[] args) throws IOException {
        CharBuffer buffer = CharBuffer.allocate(100);
        while (fillDrain(buffer)){
            buffer.flip();
            drainBuffer(buffer);
            buffer.clear();
        }
    }

    /**
     * 填充
     * @param buffer
     * @return
     */
    public static boolean fillDrain(CharBuffer buffer) {
        if (index>=strings.length)return false;
        String string = strings[index++];
        for (int i = 0; i < string.length(); i++) {
            buffer.put(string.charAt(i));
        }
        return  true;
    }

    /**
     * 释放
     * @param buffer
     */
    private static void drainBuffer (CharBuffer buffer){
        while (buffer.hasRemaining()){
            System.out.print(buffer.get());
        }
        System.out.println();
    }

}
