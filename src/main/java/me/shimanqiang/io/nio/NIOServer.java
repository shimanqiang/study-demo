package me.shimanqiang.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author x
 * @since 2019/6/12 19:25
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        new NIOServer().start(8000);
    }

    public void start(int port) throws IOException {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        new Thread(() -> {
            try {
                // 对应IO编程中服务端启动
                ServerSocketChannel listenerChannel = ServerSocketChannel.open();
                listenerChannel.socket().bind(new InetSocketAddress(port));
                listenerChannel.configureBlocking(false);
                listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                while (true) {
                    // 监测是否有新的连接，这里的1指的是阻塞的时间为 1ms
                    if (serverSelector.select(1) > 0) {
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();

                            if (key.isAcceptable()) {
                                try {
                                    // (1) 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
                                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                } finally {
                                    keyIterator.remove();
                                }
                            }

                        }
                    }
                }
            } catch (IOException ignored) {
            }

        }).start();


        new Thread(() -> {
            try {
                while (true) {
                    // (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为 1ms
                    if (clientSelector.select(1) > 0) {
                        Set<SelectionKey> set = clientSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if (key.isReadable()) {
                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                //clientChannel.configureBlocking(false);
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                // (3) 面向 Buffer
                                int read = clientChannel.read(byteBuffer);
                                if (read < 0) {
                                    System.out.println("客户端连接断开....");
                                    //此处要关闭，否则Java NIO中断开连接后不断有OP_READ事件的问题
                                    clientChannel.close();
                                    continue;
                                }
                                byteBuffer.flip();
                                String msg = Charset.defaultCharset().newDecoder().decode(byteBuffer).toString();
                                System.out.println("echo:" + msg);

                                key.interestOps(SelectionKey.OP_WRITE);
                                //clientChannel.register(clientSelector, SelectionKey.OP_WRITE);
                            } else if (key.isWritable()) {
                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                //clientChannel.configureBlocking(false);
                                clientChannel.write(ByteBuffer.wrap("ok".getBytes()));
                                //注册事件
                                key.interestOps(SelectionKey.OP_READ);
                                //clientChannel.register(clientSelector, SelectionKey.OP_READ);
                            }
                            //移除SelectionKey
                            keyIterator.remove();
                        }
                    }
                }
            } catch (IOException ignored) {
            }
        }).start();

    }
}
