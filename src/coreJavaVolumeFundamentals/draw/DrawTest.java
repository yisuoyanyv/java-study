package coreJavaVolumeFundamentals.draw;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * @author zhangjinglong
 * @date 2020-03-11-10:58 上午
 *
 * 简单的几何图形
 */

public class DrawTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new DrawFrame();
            frame.setTitle("DrawTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * A frame that contains a panel with drawings.
 */
class DrawFrame extends JFrame {
    public DrawFrame() {
        add(new DrawComponet());
        pack();
    }
}


/**
 * A component that displays rectangle and ellipses
 */
class DrawComponet extends JComponent {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        //coreJavaVolumeFundamentals.draw a rectangle
        double leftX = 100;
        double topY = 100;
        double width = 200;
        double height = 150;

        Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
        g2.draw(rect);

        //coreJavaVolumeFundamentals.draw the enclosed ellipse
        Ellipse2D ellipse = new Ellipse2D.Double();
        ellipse.setFrame(rect);
        g2.draw(ellipse);

        //coreJavaVolumeFundamentals.draw a diagnoal line
        g2.draw(new Line2D.Double(leftX, topY, leftX + width, topY + height));

        //coreJavaVolumeFundamentals.draw a circle with the same center

        double centerX = rect.getCenterX();
        double centerY = rect.getCenterY();
        double radius = 150;

        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerX, centerX, centerX + radius, centerY + radius);
        g2.draw(circle);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    ;
}