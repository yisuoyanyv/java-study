package coreJavaVolumeFundamentals.anonymousInnerClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;

/**
 * @author zhangjinglong
 * @date 2020-02-29-4:05 下午
 */

public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        TalkingClock clock=new TalkingClock();
        clock.start(1000,true);

        //keep program running util user selects "OK"
        JOptionPane.showMessageDialog(null,"Quit program?");
        System.exit(0);
    }
}

/**
 * A clock that prints the time in regular intervals
 */
class TalkingClock{
    /**
     * Starts the clock
     * @param interval  the interval between messages (in milliseconds)
     * @param beep if ture if the cloke should beep
     */
    public void start(int interval,boolean beep){
        ActionListener listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone,the time is "+new Date());
                if(beep) Toolkit.getDefaultToolkit().beep();
            }
        };
        Timer t=new Timer(interval,listener);
        t.start();
    }
}