package coreJavaVolumeFundamentals.colorChooser;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhangjinglong
 * @date 2020-03-15-5:53 下午
 */

public class ColorChooserFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ColorChooserFrame();
            frame.setTitle("ColorChooserTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
