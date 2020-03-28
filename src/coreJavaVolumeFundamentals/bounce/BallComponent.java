package coreJavaVolumeFundamentals.bounce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The component that draws the balls.
 * @author zhangjinglong
 * @date 2020-02-11-21:20
 */

public class BallComponent extends JPanel {
    private static final int DEFAULT_WIDTH=450;
    private static final int DEFAULT_HEIGTH=350;

    private java.util.List<Ball>  balls=new ArrayList<>();

    /*
    * Add a ball to the component
    * */
    public void add(Ball b){
        balls.add(b);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);//erase background
        Graphics2D g2=(Graphics2D)g;
        for(Ball b:balls){
            g2.fill(b.getShape());
        }

    }

    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGTH);
    }


}
