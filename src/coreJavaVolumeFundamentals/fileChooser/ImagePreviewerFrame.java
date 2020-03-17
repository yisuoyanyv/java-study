package coreJavaVolumeFundamentals.fileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;


/**
 * @author zhangjinglong
 * @date 2020-03-15-4:34 下午
 * <p>
 * A frame that has a menu for loading an image and a display area for the loaded image.
 */

public class ImagePreviewerFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;
    private JLabel label;
    private JFileChooser chooser;

    public ImagePreviewerFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        //set up menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(event -> {
            chooser.setCurrentDirectory(new File("."));

            //show file chooser dialog
            int result = chooser.showOpenDialog(ImagePreviewerFrame.this);

            //if image file accepted, set it as icon of the label
            if (result == JFileChooser.APPROVE_OPTION) {
                String name = chooser.getSelectedFile().getPath();
                label.setIcon(new ImageIcon(name));
                pack();
            }

        });


        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(event -> System.exit(0));

        //user a label to display the images
        label = new JLabel();
        add(label);

        //set up file chooser
        chooser = new JFileChooser();

        //accept all image filed ending with .jpg,.jpen,.gif
        FileFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
        chooser.setFileFilter(filter);

        chooser.setAccessory(new ImagePreviewer(chooser));

        chooser.setFileView(new FileIconView(filter, new ImageIcon("resources/palette.gif")));
    }
}
