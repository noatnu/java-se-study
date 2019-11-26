package core.swing.afanihao.cn.java.p04.p0405;

import core.swing.afanihao.cn.java.RunAbs;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 彩色 label
 */
public class ColorfulLabelTest extends RunAbs implements Serializable {

    @Test
    public void testRun(){
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                JFrame jFrame = new JFrame(s) ;
                Container container = jFrame.getContentPane();
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setVisible(true);//窗体显示
                jFrame.setResizable(false);
                jFrame.setSize(1200,900);//大小
                jFrame.setLocation(60,60);

                container.setLayout(new FlowLayout());//流式布局

                List<Color> colorList = Arrays.asList( Color.YELLOW,Color.GREEN, Color.LIGHT_GRAY, Color.CYAN) ;
                for (int i = 0; i <colorList.size() ; i++) {
                    container.add(new ColorfulLabel(String.valueOf(i),colorList.get(i))) ;
                }
            }
        };
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                consumer.accept("窗体");

            }
        });
        end();
    }

    class ColorfulLabel extends JLabel
    {
        public ColorfulLabel(String text,Color bgColor){
            super(text);
            setOpaque(true);//设置背景为不透明 （默认透明 )
            setBackground(bgColor);
            setPreferredSize(new Dimension(60,30));// 设置最佳尺寸
            setHorizontalAlignment(SwingConstants.CENTER);//设置水平对齐
        }
    }

}
