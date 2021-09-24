package cn.simple.rpc.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author guochuang
 * @version v1
 * @date 2021/08/18 15:38:00
 */
public class Client {
    public static void main(String[] args) {
        int port = 9999;
        String serverName = "127.0.0.1";
        try {
            Socket client = new Socket(serverName, port);
            OutputStream outputStream = client.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("is client " + client.getLocalSocketAddress());

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    InputStream inputStream = client.getInputStream();
                    DataInputStream dataInputStream = new DataInputStream(inputStream);
                    System.out.println("服务器响应" + dataInputStream.readUTF());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
