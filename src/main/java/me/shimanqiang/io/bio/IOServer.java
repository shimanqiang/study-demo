package me.shimanqiang.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * https://juejin.im/book/5b4bc28bf265da0f60130116/section/5b4bc28b5188251b1f224ee5
 *
 * @author x
 * @since 2019/8/22 15:21
 */
public class IOServer {

    public static void main(String[] args) throws Exception {
        new IOServer().start();
    }

    private void start() throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        // (1) 接收新连接线程
        Thread workerThread = new Thread(() -> {
            while (true) {
                try {
                    // (1) 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();

                    // (2) 每一个新的连接都创建一个线程，负责读取数据
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            // (3) 按字节流方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {
                        }
                    }).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        workerThread.start();

        System.out.println("服务启动......");

        //（2）阻塞当前线程
        workerThread.join();

        System.out.println("服务停止......");
    }
}
