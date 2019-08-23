package me.shimanqiang.io.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;

/**
 * @author x
 * @since 2019/6/12 19:26
 */
public class NIOClient {
    /**
     * 使用IO Client即可
     */
    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        //写
                        socket.getOutputStream().write((new Date() + ": hello nio").getBytes());

                        //读
                        InputStream inputStream = socket.getInputStream();
                        byte[] buffer = new byte[inputStream.available()];
                        socket.getInputStream().read(buffer);
                        System.out.println(new String(buffer));

                        //阻塞2秒
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
