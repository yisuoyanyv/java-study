package coreJavaVolume2AdvancedFeatures.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zhangjinglong
 * @date 2020-03-22-10:57 下午
 * <p>
 * This program implements a simple server that listens to port 8189 and echoes back all client input.
 */

public class EchoServer {
    public static void main(String[] args) throws IOException {
        //establish server socket
        try (ServerSocket s = new ServerSocket(8189)) {
            //wait for clinet connection
            try (Socket incoming = s.accept()) {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();

                try (Scanner in = new Scanner(inStream, "UTF-8")) {
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
                    out.println("Hello! Enter BYE to exit.");

                    //echo client input
                    boolean done = false;
                    while (!done && in.hasNextLine()) {
                        String line = in.nextLine();
                        out.println("Echo: " + line);
                        if (line.trim().equals("BYE"))
                            done = true;
                    }
                }
            }
        }
    }
}
