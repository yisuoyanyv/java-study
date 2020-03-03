package coreJavaVolumeFundamentals.logging;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.*;

/**
 * A modification of the image viewer program that logs various events
 * @author zhangjinglong
 * @date 2020-02-29-10:31 下午
 */

public class LoggingImageViewer {
    public static void main(String[] args) {
        if(System.getProperty("java.util.logging.config.class")==null
        && System.getProperty("java.util.logging.config.file")==null){
            try{
                Logger.getLogger("coreJavaVolumeFundamentals.logging").setLevel(Level.ALL);
                final int LOG_ROTATION_COUNT=10;
                Handler handler=new FileHandler("%h/LoggingImageViewer.log",0,LOG_ROTATION_COUNT);
                Logger.getLogger("coreJavaVolumeFundamentals.logging").addHandler(handler);
            }catch (IOException e){
                Logger.getLogger("coreJavaVolumeFundamentals.logging").log(Level.SEVERE,
                        "Can't create log file handler",e);
            }
        }

        EventQueue.invokeLater(()->{
            Handler windowHandler=new WindowHandler();
            windowHandler.setLevel(Level.ALL);
            Logger.getLogger("coreJavaVolumeFundamentals.logging").addHandler(windowHandler);

            JFrame frame=new ImageViewerFrame();
            frame.setTitle("LoggingImageViewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Logger.getLogger("coreJavaVolumeFundamentals.logging").fine("Shoeing Frame");
            frame.setVisible(true);
        });
    }
}

/**
 * The frame that shows the image
 */
class ImageViewerFrame extends JFrame{
    private static final int DEFAULT_WIDTH=1000;
    private static final int DEFAULT_HEIGHT=800;

    private JLabel label;
    private  static Logger logger=Logger.getLogger("coreJavaVolumeFundamentals.logging");

    public ImageViewerFrame(){
        logger.entering("ImageViewerFrame","<init>");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        //set up menu bar
        JMenuBar menuBar=new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu=new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem=new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());

        JMenuItem exitItem=new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.fine("Exiting");
                System.exit(0);
            }
        });

        //use a label to display the images
        label=new JLabel();
        add(label);
        logger.exiting("ImageViewerFrame","<init>");
    }

    private class FileOpenListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            logger.entering("ImageViewerFrame.FileOpenListener","actionPerformed",e);

            //set up file chooser
            JFileChooser chooser=new JFileChooser();
            chooser.setCurrentDirectory(new File("."));

            //accept all files ending with .gif
//            chooser.setFileFilter(new FileFilter() {
//                @Override
//                public boolean accept(File f) {
//                    return f.getName().toLowerCase().endsWith(".gif") ||f.isDirectory();
//                }
//
//                @Override
//                public String getDescription() {
//                    return "GIF Images";
//                }
//            });

            //show file chooser dialog
            int r=chooser.showOpenDialog(ImageViewerFrame.this);

            //if image file accepted,set it as icon of the label
            if(r==JFileChooser.APPROVE_OPTION){
                String name=chooser.getSelectedFile().getPath();
                logger.log(Level.FINE,"Reading file {0}",name);
                label.setIcon(new ImageIcon(name));
            }else{
                logger.fine("File open dialog canceld.");
            }
            logger.exiting("ImageViewerFrame,FileOpenListener","actionPerformed");

        }
    }

}

/**
 * A handler for displaying log records in a window
 */
class WindowHandler extends StreamHandler{
    private JFrame frame;

    public WindowHandler(){
        frame=new JFrame();
        final JTextArea output=new JTextArea();
        output.setEnabled(false);
        frame.setSize(400,800);
        frame.add(new JScrollPane(output));
        frame.setFocusableWindowState(false);
        frame.setVisible(true);
        setOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                output.append(new String(b,off,len));
            }
        });
    }

    @Override
    public synchronized void publish(LogRecord record) {
        if(!frame.isVisible()) return;
        super.publish(record);
        flush();
    }
}