package coreJavaVolumeFundamentals.radioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author zhangjinglong
 * @date 2020-03-14-12:14 下午
 * A frame with a sample text label and radio buttons for selecting font size
 */

public class RadioButtonFrame extends JFrame {
    private JPanel buttonPanel;
    private ButtonGroup buttonGroup;
    private JLabel label;
    private static final int DEFAULT_SIZE = 36;

    public RadioButtonFrame() {
        //add the sample text label
        label = new JLabel("The quick brown fox jumps over the lazy dog");
        label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
        add(label, BorderLayout.CENTER);

        //add the radios buttons;

        buttonPanel = new JPanel();
        buttonGroup = new ButtonGroup();

        addRadioButton("Small", 8);
        addRadioButton("Medium", 12);
        addRadioButton("Larger", 18);
        addRadioButton("Extra lager", 36);

        add(buttonPanel, BorderLayout.SOUTH);
        pack();


    }

    /**
     * Adds a radio button that sets the font size of the sample text.
     *
     * @param name
     * @param size
     */
    private void addRadioButton(String name, int size) {
        boolean selected = size == DEFAULT_SIZE;
        JRadioButton button = new JRadioButton(name, selected);
        buttonGroup.add(button);
        buttonPanel.add(button);

        //this listener sets the label font size
        ActionListener listener = event -> label.setFont(new Font("Serif", Font.PLAIN, size));
        button.addActionListener(listener);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new RadioButtonFrame();
            frame.setTitle("radio Buttons");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
