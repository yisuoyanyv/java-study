package coreJavaVolumeFundamentals.optionDialog;

import java.awt.*;
import javax.swing.*;

/**
 * @author Cay Horstmann
 * @version 1.34 2015-06-12
 */
public class OptionDialogTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new OptionDialogFrame();
            frame.setTitle("OptionDialogTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
