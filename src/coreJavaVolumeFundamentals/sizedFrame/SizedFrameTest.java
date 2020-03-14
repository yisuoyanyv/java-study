package coreJavaVolumeFundamentals.sizedFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhangjinglong
 * @date 2020-03-11-9:49 上午
 */

public class SizedFrameTest {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame frame = new SizedFrame();
            frame.setTitle("SizeFrame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class SizedFrame extends JFrame {
    public SizedFrame() {
        //get screen dimension
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        //set frame width,height and let platform pick screen location

        setSize(screenWidth / 2, screenHeight / 2);
        setLocationByPlatform(true);

        //set frame icon
        Image img = new ImageIcon("icon.gif").getImage();
        setIconImage(img);

    }
}