package coreJavaVolumeFundamentals.checkBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A frame with a sample text label and check boxes for selecting font attributes.
 *
 * @author zhangjinglong
 * @date 2020-03-14-11:55 上午
 */

public class CheckBoxFrame extends JFrame {
    private JLabel label;
    private JCheckBox bold;
    private JCheckBox italic;
    private static final int FONTSIZE = 24;

    public CheckBoxFrame() {
        //add the sample text label
        label = new JLabel("The quick brown fox jumps over the lazy dog.");
        label.setFont(new Font("Serif", Font.BOLD, FONTSIZE));
        add(label, BorderLayout.CENTER);

        //this listener sets the font attribute of the label to the check box state

        ActionListener listener = event -> {
            int mode = 0;
            if (bold.isSelected()) mode += Font.BOLD;

            if (italic.isSelected()) mode += Font.ITALIC;
            label.setFont(new Font("Serif", mode, FONTSIZE));

        };

        //add the check boxes
        JPanel buttonPanel = new JPanel();

        bold = new JCheckBox("Bold");
        bold.addActionListener(listener);
        bold.setSelected(true);
        buttonPanel.add(bold);

        italic = new JCheckBox("Italic");
        italic.addActionListener(listener);
        italic.setSelected(false);
        buttonPanel.add(italic);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame jFrame = new CheckBoxFrame();
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setTitle("Check Box");
            jFrame.setVisible(true);
        });
    }

}
