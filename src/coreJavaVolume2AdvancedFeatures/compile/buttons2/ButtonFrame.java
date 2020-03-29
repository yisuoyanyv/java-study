package coreJavaVolume2AdvancedFeatures.compile.buttons2;

import javax.swing.*;

/**
 * @author zhangjinglong
 * @date 2020-03-29-10:02 上午
 */

public abstract class ButtonFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    protected JPanel panel;

    protected JButton yellowButton;
    protected JButton blueButton;
    protected JButton redButton;

    protected abstract void addEventHandlers();

    public ButtonFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        panel = new JPanel();
        add(panel);

        yellowButton = new JButton("Yellow");
        blueButton = new JButton("Blue");
        redButton = new JButton("Red");

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        addEventHandlers();
    }
}
