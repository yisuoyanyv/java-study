package coreJavaVolume2AdvancedFeatures.write;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhangjinglong
 * @date 2020-03-21-9:47 下午
 * <p>
 * This program shows thow to write an XML file. It saves a file describing a modern drawing in SVG fromat.
 */

public class XMLWriteTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new XMLWriterFrame();
            frame.setTitle("XMLWriteTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
