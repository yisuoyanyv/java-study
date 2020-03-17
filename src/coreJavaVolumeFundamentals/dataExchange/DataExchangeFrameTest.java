package coreJavaVolumeFundamentals.dataExchange;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhangjinglong
 * @date 2020-03-15-11:50 上午
 */

public class DataExchangeFrameTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new DataExchangeFrame();
            frame.setTitle("DataExchange ");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
