package coreJavaVolumeFundamentals.innerClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @author zhangjinglong
 * @date 2020-02-29-3:18 下午
 */

public class InnerClassTest {
    public static void main(String[] args) {
        TalkingClock clock=new TalkingClock(1000,true);
        clock.start();

        //keep program ruuning until user selects "OK"
        JOptionPane.showMessageDialog(null,"Quit program");
        System.exit(0);
    }
}

/**
 * A clock that prints the time in regular intervals
 */
class TalkingClock{
    private int interval;
    private boolean beep;

    /**
     * Constructs a talking clock
     * @param interval  the interval between messages(in milliseconds)
     * @param beep ture if the clock should beep
     */
    public TalkingClock(int interval,boolean beep){
        this.beep=beep;
        this.interval=interval;
    }

    /**
     * Starts the clock
     */
    public void start(){
        ActionListener listener=new TimePrinter();
        Timer t=new Timer(interval,listener);
        t.start();
    }

    public class TimePrinter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("At the tone, the time is "+new Date());
            if(beep) Toolkit.getDefaultToolkit().beep();
        }
    }

}