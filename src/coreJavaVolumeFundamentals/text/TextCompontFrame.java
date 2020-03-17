package coreJavaVolumeFundamentals.text;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhangjinglong
 * @date 2020-03-14-11:41 上午
 */

public class TextCompontFrame extends JFrame {
    public static final int TEXTAREA_ROWS = 8;
    public static final int TEXTAREA_COLUMNS = 20;

    public TextCompontFrame() {
        JTextField textField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 2));
        northPanel.add(new JLabel("User name:", SwingConstants.RIGHT));
        northPanel.add(textField);
        northPanel.add(new JLabel("PassWord:", SwingConstants.RIGHT));
        northPanel.add(passwordField);

        add(northPanel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        //add button to append text into the text area
        JPanel sourthPanel = new JPanel();


        JButton insertButton = new JButton("Insert");
        sourthPanel.add(insertButton);
        insertButton.addActionListener(event -> {
            textArea.append("User name:" + textField.getText() + " Password:" + new String(passwordField.getPassword()) + "\n");

        });

        add(sourthPanel, BorderLayout.SOUTH);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new TextCompontFrame();
            frame.setTitle("Show Text Compoent");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
