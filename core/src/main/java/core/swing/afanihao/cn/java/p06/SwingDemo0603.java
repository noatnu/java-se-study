package core.swing.afanihao.cn.java.p06;

import core.swing.AsSimpleLayout;
import core.swing.SwingUtils;
import core.swing.afanihao.cn.java.RunAbs;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义布局优化
 */
public class SwingDemo0603 extends RunAbs {
    @Test
    public void testRun() {
        Map<String, JLabel> map = new HashMap<>();
        for (int i = 0; i < 2; i++) {
            String key = RandomStringUtils.randomNumeric(7);
            map.put(String.valueOf(i), SwingUtils.getJLabel(key));
        }
        JFrame jFrame = SwingUtils.getJFrame();
        Container container = jFrame.getContentPane();


        // 设置一个自定义的布局器
        LayoutManager layoutMgr = new SimpleLayout(map.get("0"), map.get("1"));
        container.setLayout(layoutMgr);

        // 由于没有布局器，所以默认地，子控件无法显示
        container.add(map.get("0"));
        container.add(map.get("1"));
        end();
    }

    public class SimpleLayout extends AsSimpleLayout {
        private JLabel a1;
        private JLabel a2;

        public SimpleLayout(JLabel a1, JLabel a2) {
            this.a1 = a1;
            this.a2 = a2;
        }

        @Override
        public void layoutContainer(Container parent) {
            int w = parent.getWidth(); // 父窗口的宽度 width
            int h = parent.getHeight(); // 父窗口的高度 height
            System.out.println("父容器: " + w + ", " + h);

            // a1 显示在中间, 以 Preferred Size 作为大小
            if (a1.isVisible()) {
                // 得取该控件所需的显示尺寸
                Dimension size = a1.getPreferredSize();
                //System.out.println(size);
                int x = (w - size.width) / 2;
                int y = (h - size.height) / 2;

                // 在设置子控件位置时，其坐标是相对于父窗口的
                // (0,0) 就是父窗口的左上角
                a1.setBounds(x, y, size.width, size.height);
            }

            // a2 显示在右上角
            if (a2.isVisible()) {
                Dimension size = a2.getPreferredSize();
                //System.out.println(size);
                int x = (w - size.width);
                int y = 0;
                a2.setBounds(x, y, size.width, size.height);
            }
        }
    }
}
