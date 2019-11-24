package core.swing.afanihao.cn.java.p03.p0302;

import core.swing.afanihao.cn.java.RunAbs;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Date;
import java.util.function.Consumer;

/**
 * 监听器 使用  简单使用
 */
public class MyFrame extends JFrame {
    private volatile JLabel timeLabel = new JLabel(StringUtils.repeat("00:", 4));
    private volatile JButton button = new JButton("显示时间");

    private MyFrame(String title) throws HeadlessException {
        super(title);//调用父类构造器
        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        //向面板容器  里添加组件
        container.add(timeLabel);
        container.add(button);

        setResizable(true);
        setVisible(true);

        MyButtonListener buttonListener = new MyButtonListener();
        button.addActionListener(buttonListener);
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

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                consumer.accept("窗体");

            }
        });

        RunAbs.endStatic();
    }

    /**
     * 定义一个监听器
     */
    private class MyButtonListener implements ActionListener,Serializable
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("MyButtonKistener.actionPerformed");

            showTime();
//            MyFrame.this.showTime();//内部类运行外部类方法
            System.out.println(e.getActionCommand());
            System.out.println(e.getModifiers());
            System.out.println(e.getID());
            System.out.println(e.getWhen());
        }
    }


    private void showTime() {
        timeLabel.setText(DateFormatUtils.format(new Date(),DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.getPattern()));
    }

}
