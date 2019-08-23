package me.shimanqiang.io.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author x
 * @since 2019/8/22 15:21
 */
public class IOClient {
    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello bio").getBytes());
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
