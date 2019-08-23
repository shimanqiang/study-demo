package me.shimanqiang.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author x
 * @since 2019/6/12 19:25
 */
public class NIOServer2 {
    public static void main(String[] args) throws Exception {
        new NIOServer2().start(4321);
    }

    public void start(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        ServerSocket serverSocket = serverChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));

        Selector selector = Selector.open();


        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer buffer = ByteBuffer.allocate(2);

        for (; ; ) {
            //System.out.println("000000000000");
            int select = selector.select();
            if (select < 0) {
                continue;
            }
            Set<SelectionKey> readKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                try {
                    if (key.isConnectable()) {
                        System.out.println("1111");
                    } else if (key.isAcceptable()) {
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel clientChannel = channel.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();

                        StringBuilder sb = new StringBuilder();
                        int read;
                        do {
                            buffer.clear();
                            read = clientChannel.read(buffer);
                            buffer.flip();
                            sb.append(Charset.forName("UTF-8").decode(buffer).toString());
                        } while (read != 0);

                        System.out.println(sb.toString());
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_WRITE);
                    } else if (key.isWritable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        key.cancel();
                        key.channel().close();
                    } catch (Exception e1) {
                        //ignore close
                    }
                }
            }
        }
    }
}
