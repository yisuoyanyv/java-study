package coreJavaVolumeFundamentals.fileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import java.io.File;

/**
 * @author zhangjinglong
 * @date 2020-03-15-4:55 下午
 * <p>
 * A file view that displays an icon for all files that match a file filter
 */

public class FileIconView extends FileView {
    private FileFilter filter;
    private Icon icon;

    /**
     * Constructs a FileIconView
     *
     * @param aFilter a file filter -all files that this filter accepts will be shown with the icon
     * @param anIcon  the icon shown with all accepted files
     */
    public FileIconView(FileFilter aFilter, Icon anIcon) {
        filter = aFilter;
        icon = anIcon;
    }

    public Icon getIcon(File f) {
        if (!f.isDirectory() && filter.accept(f)) return icon;
        return null;
    }
}
