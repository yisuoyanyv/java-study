package coreJavaVolume2AdvancedFeatures.inetAddress;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author zhangjinglong
 * @date 2020-03-22-10:47 下午
 * <p>
 * This program demonstrates the InetAddress class.Supply a host name as command-line argument,
 * or run without command-line argument to see the address of the local host.
 */

public class InetAddressTest {
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            String host = args[0];
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for (InetAddress addr : addresses) {
                System.out.println(addr);
            }

        } else {
            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println(localHostAddress);
        }
    }
}
