package coreJavaVolumeFundamentals.font;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * @author zhangjinglong
 * @date 2020-03-11-11:43 上午
 */

public class FontTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new FontFrame();
            frame.setTitle("FontTest");
            ;
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}


/**
 * A frame with a text message component
 */
class FontFrame extends JFrame {
    public FontFrame() {
        add(new FontCompoent());
        pack();

    }
}

/**
 * A componet that shows a centered message in a box
 */
class FontCompoent extends JComponent {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        String message = "Hello,World,good ";

        Font f = new Font("Serif", Font.BOLD, 36);
        g2.setFont(f);

        //measure the size of the message

        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = f.getStringBounds(message, context);

        //set(x,y)=top left corner of text

        double x = (getWidth() - bounds.getWidth()) / 2;
        double y = (getHeight() - bounds.getHeight()) / 2;

        //add ascent to y to reach the baseline
        double ascent = -bounds.getY();
        double baseY = y + ascent;

        //coreJavaVolumeFundamentals.draw the message
        g2.drawString(message, (int) x, (int) baseY);

        g2.setPaint(Color.LIGHT_GRAY);

        //coreJavaVolumeFundamentals.draw the baseline
        g2.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));

        //drawing the enclosing rectangle

        Rectangle2D rect = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
        g2.draw(rect);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}