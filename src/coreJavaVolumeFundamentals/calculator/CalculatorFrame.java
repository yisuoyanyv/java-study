package coreJavaVolumeFundamentals.calculator;

import javax.swing.*;

/**
 * @author zhangjinglong
 * @date 2020-03-14-11:09 上午
 */

public class CalculatorFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 800;

    public CalculatorFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JPanel panel = new CalculatorPanel();
        add(panel);
        pack();
    }


}
