package coreJavaVolumeFundamentals.fileChooser;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhangjinglong
 * @date 2020-03-15-5:03 下午
 */

public class ImagePreviewerFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ImagePreviewerFrame();
            frame.setTitle("ImagePreview ");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
