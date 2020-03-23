package coreJavaVolume2AdvancedFeatures.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zhangjinglong
 * @date 2020-03-22-10:33 下午
 * <p>
 * This program makes a socket connection to the atomic clock in Boulder,Colorado,and prints
 * the time that the server sends.
 */

public class SocketTest {
    public static void main(String[] args) throws IOException {
        try (Socket s = new Socket("time-a.nist.gov", 13); Scanner in = new Scanner(s.getInputStream(), "UTF-8")) {
            while (in.hasNext()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        }
    }

}
