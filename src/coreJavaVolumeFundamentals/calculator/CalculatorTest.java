package coreJavaVolumeFundamentals.calculator;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhangjinglong
 * @date 2020-03-14-11:08 上午
 */

public class CalculatorTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new CalculatorFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Calculator");
            frame.setVisible(true);
        });
    }
}
