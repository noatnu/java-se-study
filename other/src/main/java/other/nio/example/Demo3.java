package other.nio.example;


import org.junit.jupiter.api.Test;
import tool.log.LogUtilA;

import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo3 {

    private final Logger loggerA = LogUtilA.setLoggerHanlder(Level.ALL);

    @Test
    public void mark(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        loggerA.info("未填充之前:"+print(byteBuffer));

        byteBuffer.put((byte)'h').put((byte)'e');
        loggerA.info("mark() 标记之前:"+print(byteBuffer));

        byteBuffer.position(byteBuffer.position()+0).mark(); //=========>mark  目前的position index就为标记的index index=2

        byteBuffer.put((byte)'l').put((byte)'l').put((byte)'o');
        loggerA.info("mark() 标记之后:"+print(byteBuffer));

        //当然还可以这样
        //byteBuffer.position(2);byteBuffer.mark();//===> mark index 也是 2
        //mark() 就是把当前的位置标记起来 假如一个都没填充并且也没主动设置position(number) 那么mark标记的位置就是0


        //假如此时我们释放元素就可以得到我们所希望的数据,但是此时我们启用标记的位置
        byteBuffer.reset();
        //启用之前mark()标记的位置，对了假如此前并没有标记某个位置那么是会抛出异常的 InvalidMarkException
        //另外 如果新设定的值比当前的标记小，调用limit( )或 position( )带有索引参数的版本会抛弃标记
        loggerA.info("启用标记之后:"+print(byteBuffer));//position=2而不是position=0

        //通过上面一系列的打印结果可以看到如果仅仅是标记其实对我们的代码影响为0,那么就让我们来见证奇迹吧!
        byteBuffer.limit(byteBuffer.position()).position(0);//byteBuffer.flip(); //翻转
        loggerA.info("翻转之后:"+print(byteBuffer));

        //mark() 在填充前 或者翻转后都可以设置的
    }

    public String print(ByteBuffer byteBuffer){
        StringBuilder builder = new StringBuilder(1024);
        builder.append("返回此缓冲区的容量:"+byteBuffer.capacity()+" 返回此缓冲区的限制:"+byteBuffer.limit());
        builder.append(" 返回此缓冲区的位置:"+byteBuffer.position());
        return builder.toString();
    }
}
