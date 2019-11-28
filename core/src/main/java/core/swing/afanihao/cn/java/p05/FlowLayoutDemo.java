package core.swing.afanihao.cn.java.p05;

import core.swing.afanihao.cn.java.RunAbs;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class FlowLayoutDemo extends RunAbs {

    @Test
    public void testRun() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                JFrame jFrame = new JFrame(s);
                Container container = jFrame.getContentPane();
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setVisible(true);//窗体显示
                jFrame.setResizable(false);
                jFrame.setSize(1200, 1900);//大小
                jFrame.setLocation(60, 60);

                LayoutManager layoutManager = new FlowLayout(FlowLayout.LEFT);
                container.setLayout(layoutManager);

                for (int i = 0; i < 1400; i++) {
                    switch (i) {
                        case 0: {
                            JLabel label = new JLabel(String.valueOf(i));
                            setJLabelAttribute(label, Color.YELLOW);
                            container.add(label);
                            break;
                        }
                        case 1: {
                            JLabel label = new JLabel(String.valueOf(i));
                            setJLabelAttribute(label, Color.LIGHT_GRAY);
                            container.add(label);
                            break;
                        }
                        case 2: {
                            JLabel label = new JLabel(String.valueOf(i));
                            setJLabelAttribute(label, Color.GREEN);
                            container.add(label);
                            break;
                        }
                        case 3: {
                            JLabel label = new JLabel(String.valueOf(i));
                            setJLabelAttribute(label, Color.WHITE);
                            container.add(label);
                            break;
                        }
                        default: {
                            container.add(getJLabelAttribute(new JLabel(String.valueOf(i)))) ;
                            break;
                        }
                    }
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

    private void setJLabelAttribute(JLabel label, Color color) {
        label.setBackground(color);
        label.setOpaque(true);
        label.setPreferredSize(new Dimension(60, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private JLabel getJLabelAttribute(JLabel label) {
        label.setBackground(getHSBColorRandom());
//        label.setBackground(Color.PINK);
        label.setOpaque(true);
        label.setPreferredSize(new Dimension(60, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }


}
