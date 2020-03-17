package coreJavaVolumeFundamentals.dialog;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhangjinglong
 * @date 2020-03-15-10:59 上午
 */

public class AbountDialogTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new DialogFrame();
            frame.setTitle("Test dialog");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
