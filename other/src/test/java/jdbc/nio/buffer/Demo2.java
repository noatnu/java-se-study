package jdbc.nio.buffer;



import help.Zhou_String;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by zhou on 17-12-23.
 * buffer方法详解
 */
public class Demo2 {
    public static void main(String[] args) {
        isGetMoreData();
    }


    /*Buffer的clear()学习*/
    public static void isClear(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 'z').put((byte)'h').put((byte)'o').put((byte)'u');
        System.out.println("remaining "+buffer.remaining());
        System.out.println("position and limit:"+buffer);

        buffer.clear();//作用 position=0 limit=capacity
        System.out.println(buffer+" "+buffer.remaining());
        //ps 虽然position 被设置为了0,但是缓冲区中的数据并没有消失,当然你可以向里面填充数据
        buffer.put((byte)new Random(47).nextInt(20));
        System.out.println(buffer);//又可以继续被填充数据了,其实1处是有数据的但是被新填进来的数据覆盖了
    }

    /*limit() position()初步设置*/
    public final static void isLimitAndPosition(){
        ByteBuffer buffer = ByteBuffer.allocateDirect(12);
        //初步创建并且设置直接缓冲器的limit以及position
        System.out.println("设置之前: position"+buffer.position()+" limit:"+buffer.limit());
        buffer.limit(10).position(2);
        System.out.println("设置之后: position"+buffer.position()+" limit:"+buffer.limit());
    }

    /*mark() reset() 学习*/
    public final static void isMark(){
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        buffer.position(2).mark();
        buffer.put((byte) 1);//填充一个字节
        System.out.println("--->"+buffer);
        buffer.reset();//回到最初position的位置
        System.out.println("<---"+buffer);
    }

    /**remaining() flip() rewind()**/
    public final static void isFlib(){
        ByteBuffer buffer = ByteBuffer.allocateDirect(12);
        buffer.put((byte)'c').put((byte)'h').put((byte)'i').put((byte)'n').put((byte)'a');
        System.out.println("-->"+buffer);

        System.out.println("修改数据!");//修改数据,并继续填充
        buffer.put(0,(byte)'M').put((byte)'W');

        //假如我不想填充了,要读取出来
        //需要翻转
//        buffer.limit(buffer.position()).position(0);
//        buffer.flip();//此函数代表了上面一行的意思

        int kk = buffer.position();
        buffer.rewind();//效果和flip()差不多但是不影响界限(上界),因此最好是先设置界限,如果不设置的话就会有未定义数据被读取出来
        buffer.limit(kk);//这样设置之后就和flip()效果一致了

        //读取或者说是释放
        int num = buffer.remaining();//remaining返回当前位置与限制之间的元素数
        byte[] bs = new byte[num];
        for (int i = 0; i < num; i++) {
            bs[i] = buffer.get();
        }

        //另一种方式
        int i = 0;
        if (buffer.hasRemaining()){
            bs[i++] = buffer.get();
        }

        System.out.println("打印出来的数据是:"+new String(bs));
        System.out.println("读取之后元素数量: "+buffer.remaining());

    }

    /*批量移动数据*/
    public final static void isGetMoreData(){
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024*2);
        int limitN = buffer.limit();
        byte[] bs = new byte[limitN];

        StringBuilder builder = new StringBuilder(1024);
        for (int i = 0; i <limitN ; i++) {
            builder.append(Zhou_String.toOther(3)+" ");
        }



        byte[] bytes = new byte[limitN];
        for (int i = 0; i < limitN; i++) {
            bytes[i] = builder.toString().getBytes()[i];
        }

//        buffer.put(builder.toString().getBytes());//填充数据,和下面一致
        buffer.put(bytes,0,bytes.length);//批量填充

        buffer.flip();
        buffer.get(bs,0,buffer.remaining());//批量取出
        

        System.out.println("打印数据:"+new String(bs));
    }

    /*数据压缩*/
    public static void isCompact() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 'H').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');
        System.out.println(buffer.toString());

        //取2个出来
//        buffer.flip();
        buffer.limit(buffer.position()).position(0);//准备  开始取
        System.out.println(buffer.toString());

        for (int i = 0; i < 2; i++) {
            if (buffer.hasRemaining()) System.out.println("get()" + (char) buffer.get());
        }

        System.out.println(buffer.toString());


        //假如还需要继续存,但是此缓冲区的前面两个已经变为了空字节了
        //这时compact()复制后面的字节并且把缓冲器变为了可以继续填充
        buffer.compact();

        System.out.println(buffer.toString());
        //...............继续填充
    }

    public static void isEquals(){
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        ByteBuffer buffer2 = ByteBuffer.allocate(10);
        buffer1.put((byte) Zhou_String.toLowerCase(2).charAt(0));
        buffer1.put((byte) Zhou_String.toLowerCase(2).charAt(0));
        if (buffer1.equals(buffer2)) System.out.println("yes");else System.out.println("no");;
        /**
          两个对象类型相同。包含不同数据类型的 buffer 永远不会相等,而且 buffer
         绝不会等于非 buffer 对象。
          两个对象都剩余同样数量的元素。Buffer 的容量不需要相同,而且缓冲区中剩
         余数据的索引也不必相同。但每个缓冲区中剩余元素的数目(从位置到上界)必须相
         同。
         
         在每个缓冲区中应被 Get()函数返回的剩余数据元素序列必须一致。

         */
    }


    /*链式方法学习*/
    public static void isit(){
        StringBuilder builder = new StringBuilder(20);
        builder.append("H").append("e").append("l").append("l").append("o");
        System.out.println(builder.toString());
    }
}
