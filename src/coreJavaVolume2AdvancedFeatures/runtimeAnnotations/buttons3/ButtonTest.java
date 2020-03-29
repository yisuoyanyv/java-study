package coreJavaVolume2AdvancedFeatures.runtimeAnnotations.buttons3;

import java.awt.*;
import javax.swing.*;

/**
 * @author Cay Horstmann
 * @version 1.01 2016-05-10
 */
public class ButtonTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {

            ButtonFrame frame = new ButtonFrame();
            frame.setTitle("ButtonTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}