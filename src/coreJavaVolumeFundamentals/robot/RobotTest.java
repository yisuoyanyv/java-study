package coreJavaVolumeFundamentals.robot;

import coreJavaVolumeFundamentals.button.ButtonFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * @author zhangjinglong
 * @date 2020-03-15-6:26 下午
 */

public class RobotTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            //make frame with a button pannel
            ButtonFrame frame = new ButtonFrame();
            frame.setTitle("ButtonsTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

        //attach a robot to the screen device
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice screen = environment.getDefaultScreenDevice();

        try {
            final Robot robot = new Robot(screen);
            robot.waitForIdle();
            new Thread() {
                @Override
                public void run() {
                    runTest(robot);
                }
            }.start();
        } catch (AWTException e) {
            e.getStackTrace();
        }


    }

    /**
     * Runs a sample test procedure
     *
     * @param robot the robot attached to the screen device
     */
    public static void runTest(Robot robot) {
        //simulate a space bar press
        robot.keyPress(' ');
        robot.keyRelease(' ');

        System.out.println("has pressed");
        //simulate  a tab key followed by a space
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(' ');
        robot.keyRelease(' ');

        //simulate a mouse click over the rightmost button
        robot.delay(2000);
        robot.mouseMove(220, 40);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);


        //capture the screen and show the resulting image
        robot.delay(2000);
        BufferedImage image = robot.createScreenCapture(new Rectangle(0, 0, 400, 300));

        ImageFrame frame = new ImageFrame(image);
        frame.setVisible(true);

    }

}

/**
 * A frame to display a captured image
 */
class ImageFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 350;

    /**
     * @param image the image to display
     */
    public ImageFrame(Image image) {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("Capture");

        JLabel label = new JLabel(new ImageIcon(image));
        add(label);
    }

}