package coreJavaVolumeFundamentals.dialog;


import javax.swing.*;

/**
 * @author zhangjinglong
 * @date 2020-03-15-10:42 上午
 */

public class DialogFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private AboutDialog dialog;

    public DialogFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        //Construct a File memu.
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        //Add about and Exit Menu items.
        //The About item shows the About dialog

        JMenuItem abountItem = new JMenuItem("About");
        abountItem.addActionListener(event -> {
            if (dialog == null)  //first time
                dialog = new AboutDialog(DialogFrame.this);
            dialog.setVisible(true); //pop up dialog

        });

        fileMenu.add(abountItem);

        //The Exit item exits the program.
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(event -> System.exit(0));
        fileMenu.add(exitItem);
    }


}
