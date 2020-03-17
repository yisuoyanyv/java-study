package coreJavaVolumeFundamentals.dialog;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhangjinglong
 * @date 2020-03-15-10:52 上午
 */

/**
 * A sample modal dialog that displays a message and waits for the user to click the OK button.
 */
public class AboutDialog extends JDialog {

    public AboutDialog(JFrame owner) {
        super(owner, "Abount DialogTest", true);

        //add HTML label to center

        add(new JLabel("<html><h1><i>Core Java</i><h1><hr>By Cay HortMan</html>"), BorderLayout.CENTER);

        //OK button closes the dialog
        JButton ok = new JButton("OK");
        ok.addActionListener(event -> setVisible(false));

        //add Ok button to southern boarder
        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        pack();
    }
}
