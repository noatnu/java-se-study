package core.swing.afanihao.cn.java.p03;

import core.swing.afanihao.cn.java.RunAbs;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

/**
 * 模拟监听器  但是不实现
 */
public class MyFrame extends JFrame {
    private JLabel timeLabel = new JLabel(StringUtils.repeat("00:", 4));
    private JButton button = new JButton("显示时间");

    private MyFrame(String title) throws HeadlessException {
        super(title);//调用父类构造器
        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        //向面板容器  里添加组件
        container.add(timeLabel);
        container.add(button);

        setResizable(true);
        setVisible(true);
    }


    @Test
    public void testRun() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                MyFrame myFrame = new MyFrame(s);
                myFrame.setSize(500, 500);
            }
        };

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                consumer.accept("窗体");

            }
        });

        RunAbs.endStatic();
    }


    public void showTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ff");
        String str = sdf.format(new Date());
        timeLabel.setText(str);
    }

}
