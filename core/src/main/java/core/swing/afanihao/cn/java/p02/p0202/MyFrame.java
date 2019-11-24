package core.swing.afanihao.cn.java.p02.p0202;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiFunction;

public class MyFrame extends JFrame {

    private static MyFrame myFrame = null;

    private MyFrame(String title) throws HeadlessException {
        super(title);

        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(new JLabel("hello")) ;
        container.add(new JButton("button")) ;
    }

    public static BiFunction accept(String title) {
        BiFunction<Integer, Integer, MyFrame> biFunction = new BiFunction<Integer, Integer, MyFrame>() {
            @Override
            public MyFrame apply(Integer a, Integer b) {
                if (myFrame == null) {
                    myFrame = new MyFrame(title);
                }
                if (a != null && b != null) {
                    myFrame.setSize(new Dimension(a, b));
                }
                myFrame.setVisible(true);
                return myFrame;
            }
        };
        return biFunction;
    }


}
