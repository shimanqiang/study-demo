package me.shimanqiang.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZkConnection {
    static String IP_STR = "192.168.8.118:2181";
    //创建计数器对象
    static CountDownLatch countDownLatch = new CountDownLatch(1);
    //创建连接对象
    static ZooKeeper zooKeeper;

    public void zkConn() {
        try {
            zooKeeper = new ZooKeeper(IP_STR, 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println(event.toString());
                    if (event.getType().equals(Event.EventType.None)) {
                        if (event.getState() == Event.KeeperState.SyncConnected) {
                            //解除线程阻塞
                            countDownLatch.countDown();
                            System.out.println("连接创建成功");
                        } else if (event.getState() == Event.KeeperState.Disconnected) {
                            System.out.println("断开连接");
                        } else if (event.getState() == Event.KeeperState.Expired) {
                            System.out.println("会话超时");
                        } else if (event.getState() == Event.KeeperState.AuthFailed) {
                            System.out.println("认证失败");
                        }
                    }
                }
            });
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ZkConnection zkConnection = new ZkConnection();
        zkConnection.zkConn();
        TimeUnit.SECONDS.sleep(5);
    }
}
