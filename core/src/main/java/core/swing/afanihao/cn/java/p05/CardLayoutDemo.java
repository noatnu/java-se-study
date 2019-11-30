package core.swing.afanihao.cn.java.p05;

import core.swing.SwingUtils;
import core.swing.afanihao.cn.java.RunAbs;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CardLayoutDemo extends RunAbs {


    @Test
    public void test2() {
        BiConsumer<JButton, JPanel> biConsumer = new BiConsumer<JButton, JPanel>() {
            @Override
            public void accept(JButton jButton, JPanel jPanel) {
                LayoutManager layoutMgr = jPanel.getLayout();
                if (layoutMgr instanceof CardLayout) {
                    CardLayout cardLayout = (CardLayout) layoutMgr;
                    String text = jButton.getText();
                    switch (text) {
                        case "a":
                            cardLayout.show(jPanel, "button");
                            break;
                        case "b":
                            cardLayout.show(jPanel, "text");
                            break;
                        default:
                            break;
                    }
                }
            }
        };


        JFrame jFrame = SwingUtils.getJFrame("窗体");
        Container container = jFrame.getContentPane();
        container.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new CardLayout());
        JButton jButtonA = new JButton("a");
        JButton jButtonB = new JButton("b");
        jButtonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                biConsumer.accept(jButtonA, cardPanel);
            }
        });
        jButtonB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                biConsumer.accept(jButtonB, cardPanel);
            }
        });
        buttonPanel.add(jButtonA);
        buttonPanel.add(jButtonB);
        container.add(buttonPanel, BorderLayout.PAGE_START);
        container.add(cardPanel, BorderLayout.CENTER);//最好是在button放入面板之前加入主面板



        // 创建第一个面板
        JPanel p1 = new JPanel();
        p1.add(new JButton("红"));
        p1.add(new JButton("绿"));
        p1.add(new JButton("蓝"));

        // 创建第二个面板
        JPanel p2 = new JPanel();
        p2.add(new JLabel("输入"));
        p2.add(new JTextField(20));

        cardPanel.add(p1, "button");
        cardPanel.add(p2, "text");



        end();
    }

    @Test
    public void testRun() {
        Function<String, JFrame> function = new Function<String, JFrame>() {
            @Override
            public JFrame apply(String s) {
                JFrame jFrame = new JFrame(StringUtils.isNotEmpty(s) ? s : RandomStringUtils.random(12));
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setVisible(true);//窗体显示
                jFrame.setResizable(true);
                jFrame.setBounds(300, 200, 700, 300);
                return jFrame;
            }
        };
        JFrame jFrame = function.apply("窗体");
        Container contentPane = jFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        JComboBox<String> options = new JComboBox<>();
        // 创建一个下拉列表，供选择
        options.addItem("第一个面板");
        options.addItem("第二个面板");

        // 创建第二个面板
        JPanel jPanel = new JPanel();
        contentPane.add(options, BorderLayout.PAGE_START);
        contentPane.add(jPanel, BorderLayout.CENTER);
        jPanel.setLayout(new CardLayout());
        // 创建第一个面板
        JPanel p1 = new JPanel();
        p1.add(new JButton("红"));
        p1.add(new JButton("绿"));
        p1.add(new JButton("蓝"));

        // 创建第二个面板
        JPanel p2 = new JPanel();
        p2.add(new JLabel("输入"));
        p2.add(new JTextField(20));

        jPanel.add(p1, "button");
        jPanel.add(p2, "text");


        BiFunction<JPanel, JComboBox<String>, String> boxStringBiFunction = new BiFunction<JPanel, JComboBox<String>, String>() {
            @Override
            public String apply(JPanel jPanel, JComboBox<String> stringJComboBox) {
                LayoutManager layoutMgr = jPanel.getLayout();
                if (layoutMgr instanceof CardLayout) {
                    CardLayout cardLayout = (CardLayout) layoutMgr;
                    switch (stringJComboBox.getSelectedIndex()) {
                        case 0:
                            cardLayout.show(jPanel, "button");
                            break;
                        case 1:
                            cardLayout.show(jPanel, "text");
                            break;
                        default:
                            break;
                    }
                }
                return null;
            }
        };

        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boxStringBiFunction.apply(jPanel, options);
            }
        });

        end();
    }

}
