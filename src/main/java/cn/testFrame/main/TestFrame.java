package cn.testFrame.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

/**
 * @Description //TODO
 * @Author wangguanbo
 * @Date 16:42 2019/6/16
 * @return
 **/
public class TestFrame extends JFrame implements ActionListener {

    public static void main(String[] args) {
        TestFrame testFrame = new TestFrame();
        testFrame.setVisible(Boolean.TRUE);
    }

    private JTextField jTextField;
    private JTextArea jTextArea;
    private JButton jButton,jButton2;

    public TestFrame() throws HeadlessException {
        this.setSize(250,150);
        this.setLayout(new FlowLayout());
        this.setTitle("小程序求和计算机");
        Container contentPane = this.getContentPane();
        jTextArea = new JTextArea("",5,10);
        jTextArea.setBackground(Color.cyan);
        jTextField = new JTextField("",10);
        jTextField.setBackground(Color.red);
        jTextField.setEditable(Boolean.FALSE);
        jButton = new JButton("求     和");
        jButton2 = new JButton("重新计算");
        jButton.addActionListener(this);
        jButton2.addActionListener(this);
        add(jTextArea);
        add(jTextField);
        add(jButton);
        add(jButton2);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        int sumNum = 0;
        if (source == jButton) {
            String text = jTextArea.getText();
            StringTokenizer stringTokenizer = new StringTokenizer(text);
            while (stringTokenizer.hasMoreElements()){
                sumNum += Integer.parseInt(stringTokenizer.nextElement().toString());
            }
        }else{
            jTextArea.setText("");
            jTextField.setText("");
        }
        jTextField.setText(String.valueOf(sumNum));
    }
}
