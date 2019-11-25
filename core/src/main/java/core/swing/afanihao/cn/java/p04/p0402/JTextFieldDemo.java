package core.swing.afanihao.cn.java.p04.p0402;

import core.swing.afanihao.cn.java.RunAbs;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

/**
 * 多行文本
 */
public class JTextFieldDemo extends RunAbs {

    @Test
    public void testRun(){
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                JFrame jFrame = new JFrame(s) ;

                Container container = jFrame.getContentPane();

                JLabel jLabel = new JLabel();
                jLabel.setText("清输入字符!");
                jLabel.setFont(new Font("微软雅黑",0,14));
                jLabel.setForeground(Color.BLUE);//字体颜色
                jLabel.setBackground(Color.green);
                jLabel.setSize(50,30);



                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setVisible(true);
                jFrame.setResizable(false);
                jFrame.setSize(800,400);

                container.setLayout(new FlowLayout());
                //16列
                JTextField jTextField = new JTextField(16);
                jTextField.setFont(new Font("宋体",0,14));
                JButton jButton = new JButton("确定");
                jButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        onButtonShow(jTextField,container) ;
                    }
                });
                container.add(jLabel) ;
                container.add(jTextField) ;
                container.add(jButton) ;


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

    private void onButtonShow(JTextField jTextField,Container container ){
       String message = String.join("","输入了:",jTextField.getText());
       //消息文本 面板组件
       JOptionPane.showMessageDialog(container,message);//斜体一般都是静态方法
    }

}
