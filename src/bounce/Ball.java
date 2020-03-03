package bounce;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * a ball that moves and bounces off the edges of a rectangle
 * @author zhangjinglong
 * @date 2020-02-11-21:23
 */

public class Ball {
    private static final int XSIZE=15;
    private static final int YSIZE=15;
    private double x=0;
    private double y=0;
    private double dx=1;
    private double dy=1;

    /*
    Moves the ball to he next position,reversing direction if it hits one of the edges
     */
    public void move(Rectangle2D bounds){
        x+=dx;
        y+=dy;

        if(x<bounds.getMinX()){
            x=bounds.getMinX();
            dx=-dx;
        }

        if(x+XSIZE>=bounds.getMaxX()){
            x=bounds.getMaxX()-XSIZE;
            dx=-dx;
        }

        if(y<bounds.getMinY()){
            y=bounds.getMinY();
            dy=-dy;
        }
        if(y+YSIZE>bounds.getMaxX()){
            y=bounds.getMaxY()-YSIZE;
            dy=-dy;
        }

    }

    /*
    Gets the shape of the ball
    */
    public Ellipse2D getShape(){
        return new Ellipse2D.Double(x,y,XSIZE,YSIZE);
    }
}
