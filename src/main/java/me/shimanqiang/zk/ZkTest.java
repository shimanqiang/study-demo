package me.shimanqiang.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.cnblogs.com/LiZhiW/p/4923693.html
 *
 * @since 2019/6/4 20:21
 */
public class ZkTest {
    public static void main(String[] args) throws Exception {
        String testNode = "/test";
        String zookeeperAddress = "10.21.51.243:2181,10.21.49.252:2181,10.21.50.241:2181";
        System.out.println(zookeeperAddress);

        /**
         * 建立连接
         */
        ZooKeeper zk = new ZooKeeper(zookeeperAddress, 3000, new Watcher() {
            // 监控所有被触发的事件
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.toString());
            }
        });
        System.out.println("OK!");

        /**
         * schema
         * 设置认证
         *
         * world	只有一个用户：anyone，代表所有人（默认）
         * ip	    使用IP地址认证
         * auth	    使用已添加认证的用户认证
         * digest	使用“用户名:密码”方式认证
         */
        zk.addAuthInfo("digest", "admin:admin".getBytes());
        zk.addAuthInfo("digest", "abc:abc".getBytes());

        /**
         * 判断节点是否存在
         */
        if (zk.exists(testNode, true) == null) {
            /**
             *设置认证方式
             */
            Id ADMIN_IDS = new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin"));
            Id USER_IDS = new Id("digest", DigestAuthenticationProvider.generateDigest("abc:abc"));
            Id ANYONE_ID_UNSAFE = ZooDefs.Ids.ANYONE_ID_UNSAFE;

            /**
             *设置ACL权限
             *Create  允许对子节点Create 操作
             *Read    允许对本节点GetChildren 和GetData 操作
             *Write   允许对本节点SetData 操作
             *Delete  允许对子节点Delete 操作(本节点也可以删除)
             *Admin   允许对本节点setAcl 操作
             *ALL = READ | WRITE | CREATE | DELETE | ADMIN;
             */
            List<ACL> aclList = new ArrayList<>();
            aclList.add(new ACL(ZooDefs.Perms.ADMIN, ADMIN_IDS));
            aclList.add(new ACL(ZooDefs.Perms.CREATE | ZooDefs.Perms.READ | ZooDefs.Perms.WRITE | ZooDefs.Perms.DELETE, USER_IDS));
            aclList.add(new ACL(ZooDefs.Perms.READ, ANYONE_ID_UNSAFE));

            /**
             * 创建根节点
             */
            zk.create(testNode, "0".getBytes(), aclList, CreateMode.PERSISTENT);
        }

        /**
         * 查看ACL权限
         */
        if (zk.exists(testNode, true) != null) {
            List<ACL> craneAcl = zk.getACL(testNode, new Stat());
            System.out.println(craneAcl);
        }

        System.out.println("所有的节点");
        System.out.println(zk.getChildren("/", false));

        byte[] data = zk.getData(testNode, false, null);
        System.out.println(new String(data));

//        zk.setData(testNode, "abc".getBytes(), -1);


        String test00xNode = testNode + "/" + "test001";
//        zk.create(test00xNode, "test00x".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        System.out.println(zk.getACL(test00xNode,new Stat()));
//        byte[] data2 = zk.getData(test00xNode, false, null);
//        System.out.println(new String(data2));
//        zk.delete(test00xNode, -1);

        /**
         * 删除节点
         */
        zk.delete(testNode, -1);

        System.out.println(zk.getChildren("/", false));
    }


    private static void testChildPath(ZooKeeper zk, String path) throws Exception {
        List<String> children = zk.getChildren(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    testChildPath(zk, path);
                    System.out.println("[testChildPath]" + event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //0000000143
        if (children != null && children.size() > 0) {
            String s = children.get(0);
            zk.delete(path + "/" + s, -1);
            System.out.println("数据：" + s);
        }
    }

    private static void testSetData(ZooKeeper zk, String path) throws Exception {
        byte[] data = zk.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    ZkTest.testSetData(zk, path);
                    System.out.println("[testSetData]" + event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, null);
        System.out.println("query node :" + new String(data));
    }
}
