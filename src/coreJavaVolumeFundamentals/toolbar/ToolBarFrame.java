package coreJavaVolumeFundamentals.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author zhangjinglong
 * @date 2020-03-14-5:48 下午
 */

public class ToolBarFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private JPanel panel;

    public ToolBarFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        //add a panel for color change

        panel = new JPanel();
        add(panel, BorderLayout.CENTER);

        //set up actions
        Action blueAction = new ColorAction("Blue", new ImageIcon("resources/blue-ball.gif"), Color.BLUE);
        Action yellowAction = new ColorAction("Yellow", new ImageIcon("resources/yellow-ball.gif"), Color.YELLOW);
        Action redAction = new ColorAction("Red", new ImageIcon("resources/red-ball.gif"), Color.RED);

        Action exitAction = new AbstractAction("Exit", new ImageIcon("resources/exit.gif")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit");

        //populate coreJavaVolumeFundamentals.toolbar

        JToolBar bar = new JToolBar();
        bar.setMinimumSize(new Dimension(200, 50));
        bar.add(blueAction);
        bar.add(yellowAction);
        bar.add(redAction);
        bar.addSeparator();
        bar.add(exitAction);
        add(bar, BorderLayout.NORTH);

        //populate menu
        JMenu menu = new JMenu("Color");
        menu.add(yellowAction);
        menu.add(blueAction);
        menu.add(redAction);
        menu.addSeparator();
        menu.add(exitAction);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);


    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ToolBarFrame();
            frame.setTitle("Tool bar and menu bar");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }


    /**
     * The color action sets the background of the frame to a given color.
     */
    class ColorAction extends AbstractAction {
        public ColorAction(String name, Icon icon, Color c) {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, name + " background");
            putValue("Color", c);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color c = (Color) getValue("Color");
            panel.setBackground(c);
        }
    }
}
