package coreJavaVolumeFundamentals.border;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @author zhangjinglong
 * @date 2020-03-14-12:29 下午
 * A frame with radio buttons to pick a border style
 */

public class BorderFrame extends JFrame {
    private JPanel demoPanel;
    private JPanel buttonPanel;

    private ButtonGroup group;

    public BorderFrame() {
        demoPanel = new JPanel();
        buttonPanel = new JPanel();
        group = new ButtonGroup();

        addRadioButton("Lowered bevel", BorderFactory.createLoweredBevelBorder());
        addRadioButton("Raised bevel", BorderFactory.createRaisedBevelBorder());
        addRadioButton("Etched bevel", BorderFactory.createEtchedBorder());
        addRadioButton("Line bevel", BorderFactory.createLineBorder(Color.BLUE));
        addRadioButton("Matte bevel", BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));
        addRadioButton("Empyt bevel", BorderFactory.createEmptyBorder());

        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "Border types");
        buttonPanel.setBorder(titled);

        setLayout(new GridLayout(2, 1));
        add(buttonPanel);
        add(demoPanel);
        pack();

    }

    private void addRadioButton(String buttonName, Border b) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(event -> demoPanel.setBorder(b));
        group.add(button);
        buttonPanel.add(button);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new BorderFrame();
            frame.setTitle("Borders ");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
