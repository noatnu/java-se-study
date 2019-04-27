package jdbc.nio.buffer;

import java.nio.ByteBuffer;

/**
 * Created by zhou on 17-12-23.
 */
public class Demo1 {
    public static void main(String[] args) {
        isit();
    }

    public static void isit() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 'z').put((byte) 'h').put((byte) 'o').put((byte) 'u');
        /**
         * Java NIO一共有四个参数
         * 0 1 2 3 4 5 6 7 8 9 10
         * - - - - - - - - - - - -
         * - - - - - - - - - - - -
         * mark                limit
         * position            capacity
         * 其中capacity表示容量(ps在创建的时候被设定之后就永远不能改变)
         * position表示位置 表示下一个要读或者写的索引.位置会自动由get()和put()更新
         * limit表示缓冲区 下一个不能读和写的索引
         * mark表示标记 设定时与position()方法连用   调用reset() method即可恢复mark标记的位置
         */


        //最重要的就是读的时候是从position到limit之间,写的时候同样是position到limit之间
        /*创建最初假如不显示的设定Limit 位置那么limit和capacity一致,
        并且如果不显示的设置position的位置那么position初始位置为0*/
    }
}
