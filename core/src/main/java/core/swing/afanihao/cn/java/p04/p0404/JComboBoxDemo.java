package core.swing.afanihao.cn.java.p04.p0404;

import core.swing.afanihao.cn.java.RunAbs;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 下拉框
 */
public class JComboBoxDemo extends RunAbs implements Serializable {

    class ListOption implements Serializable{
        private String name;
        private Color color;

        ListOption(String name, Color color) {
            this.name = name;
            this.color = color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Color getColor() {
            return color;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ListOption that = (ListOption) o;
            return Objects.equals(name, that.name) &&
                    Objects.equals(color, that.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, color);
        }
    }

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

                JComboBox<ListOption> colorList = new JComboBox<>();
                colorList.addItem(new ListOption("红色",Color.RED));
                colorList.addItem(new ListOption("蓝色",Color.BLUE));
                colorList.addItem(new ListOption("绿色",Color.GREEN));

                JLabel sampleText = new JLabel("文本样例  This is a sample");
                colorList.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTextColor(colorList,sampleText) ;
                    }
                });
                container.add(colorList);
                container.add(sampleText);
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

    private void updateTextColor(JComboBox<ListOption> colorList,JLabel jLabel)
    {
        Object obj = colorList.getSelectedItem();
        if (obj instanceof ListOption){
            ListOption listOption = (ListOption)obj;
            jLabel.setText(listOption.getName());
            jLabel.setForeground(listOption.getColor());
        }
    }

}
