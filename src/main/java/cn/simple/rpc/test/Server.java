package cn.simple.rpc.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author guochuang
 * @version v1
 * @date 2021/08/18 15:35:00
 */
public class Server {
    public static void main(String args[]) {


        int port = 9999;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket server = serverSocket.accept();
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                out.writeUTF("echo " + i + "  " + server.getLocalSocketAddress() + "\n");

            }
            server.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
