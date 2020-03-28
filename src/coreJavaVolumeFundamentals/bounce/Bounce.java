package coreJavaVolumeFundamentals.bounce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Show an animated bouncing ball;
 *
 * @author zhangjinglong
 * @date 2020-02-11-21:16
 * <p>
 * 碰撞的小球
 */

public class Bounce {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame=new BounceFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });


    }
}

/*
* The frame with ball component and buttons
* */
class BounceFrame extends JFrame{
    private BallComponent comp;
    public static final int STEPS=1000;
    public static final int DELAY=3;

    public BounceFrame(){
        setTitle("Bounce");
        comp=new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel=new JPanel();
        addButton(buttonPanel,"Start",event->addBall());
        addButton(buttonPanel,"Close",event->System.exit(0));
        add(buttonPanel,BorderLayout.SOUTH);
        pack();

    }

    /**
     * Adds a button to a container.
     * @param c
     * @param title
     * @param listener
     */
    public void addButton(Container c, String title, ActionListener listener){
        JButton button=new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    /**
     * Adds a bouncing ball to the panel and makes it coreJavaVolumeFundamentals.bounce 1,000 times.
     */
    public void addBall(){

            Ball ball=new Ball();
            comp.add(ball);
            Runnable r=()->{
                try{
                    for (int i = 1; i < STEPS; i++) {
                        ball.move(comp.getBounds());
                        comp.paint(comp.getGraphics());
                        Thread.sleep(DELAY);
                    }
                }catch (InterruptedException e){

                }
            };

            Thread t=new Thread(r);
            t.start();

    }
}
