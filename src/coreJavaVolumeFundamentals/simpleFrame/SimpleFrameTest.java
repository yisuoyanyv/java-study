package coreJavaVolumeFundamentals.simpleFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhangjinglong
 * @date 2020-03-11-9:20 上午
 */

public class SimpleFrameTest {
    public static void main(String[] args) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        EventQueue.invokeLater(() -> {
            SimpleFrame frame = new SimpleFrame(screenWidth / 2, screenHeight / 2);
            frame.setLocationByPlatform(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

        System.out.println("Main Thread go over");
    }

}

class SimpleFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public SimpleFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public SimpleFrame(int width, int height) {
        setSize(width, height);
    }
}