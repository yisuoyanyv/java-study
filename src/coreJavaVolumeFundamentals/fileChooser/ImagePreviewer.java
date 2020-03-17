package coreJavaVolumeFundamentals.fileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * @author zhangjinglong
 * @date 2020-03-15-4:48 下午
 * <p>
 * A file chooser accessory that preview images
 */

public class ImagePreviewer extends JLabel {
    /**
     * Construts an ImagePreviewer
     *
     * @param chooser
     */
    public ImagePreviewer(JFileChooser chooser) {
        setPreferredSize(new Dimension(100, 100));
        setBorder(BorderFactory.createEtchedBorder());

        chooser.addPropertyChangeListener(event -> {
            if (event.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
                //the user has selected a new file
                File f = (File) event.getNewValue();
                if (f == null) {

                    setIcon(null);
                    return;
                }

                //read the image into an icon
                ImageIcon icon = new ImageIcon(f.getPath());
                //if the icon is too large to fit ,scale it
                if (icon.getIconWidth() > getWidth()) {
                    icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT));
                }
                setIcon(icon);
            }
        });

    }
}
