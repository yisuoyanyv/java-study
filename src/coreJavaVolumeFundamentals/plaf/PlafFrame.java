package coreJavaVolumeFundamentals.plaf;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhangjinglong
 * @date 2020-03-12-9:48 上午
 */

/**
 * A frame with a button Panel for changing look-and-fell
 */
public class PlafFrame extends JFrame {
    private JPanel buttonPanel;

    public PlafFrame() {
        buttonPanel = new JPanel();

        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : infos)
            makeButton(info.getName(), info.getClassName());
        add(buttonPanel);
        pack();

    }

    /**
     * Makes a button to change the pluggable look-and-feel
     *
     * @param name      the button name
     * @param className the name of the look-and-feel class
     */
    private void makeButton(String name, String className) {
        //add button to panel
        JButton button = new JButton(name);
        buttonPanel.add(button);

        //set button action
        button.addActionListener(event -> {
            //button action:switch to the new  look-and-feel
            try {
                UIManager.setLookAndFeel(className);
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new PlafFrame();
            frame.setTitle("Change look-and-feel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}
