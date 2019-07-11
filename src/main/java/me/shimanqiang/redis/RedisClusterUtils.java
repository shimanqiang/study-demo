package me.shimanqiang.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 * https://blog.csdn.net/zhoubinbin111111/article/details/52370706
 * https://www.cnblogs.com/xiaotong1223/p/9263517.html
 *
 * @author shimanqiang
 * @since 2019/7/11 15:04
 */
public class RedisClusterUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisClusterUtils.class);

    //Redis服务器IP
    private static String SERVER_ADDR_LIST = "10.48.181.94:6379,10.48.165.199:6379,10.48.153.95:6379,10.48.114.97:6379,10.48.114.71:6379,10.48.188.56:6379,10.48.164.38:6379,10.48.155.58:6379,10.48.154.149:6379,10.48.162.113:6379,10.48.168.140:6379,10.48.152.180:6379";
    //Redis的端口号
    private static int PORT = 6379;
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;
    //控制一个pool最少有多少个状态为idle(空闲的)的jedis实例，默认值也是0。
    private static int MIN_IDLE = 1;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 8;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;


    private static JedisCluster jedisCluster = null;

    /**
     * 初始化Redis连接池
     */
    static {
        init();
    }

    /**
     * 初始化redis集群
     */
    private static void init() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMinIdle(MIN_IDLE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            //向资源池借用连接时是否做连接有效性检测(ping)，无效连接会被移除，默认false，业务量很大时候建议设置为false(多一次ping的开销)
            config.setTestOnBorrow(false);
            //向资源池归还连接时是否做连接有效性检测(ping)，无效连接会被移除，默认false，业务量很大时候建议设置为false(多一次ping的开销)
            config.setTestOnReturn(false);

            Set<HostAndPort> nodes = parseServerAddr(SERVER_ADDR_LIST);
            jedisCluster = new JedisCluster(nodes, config);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 解析集群地址列表
     *
     * @param serverAddrs
     * @return
     */
    private static Set<HostAndPort> parseServerAddr(String serverAddrs) {
        if (serverAddrs == null) {
            return null;
        }
        Set<HostAndPort> nodes = new HashSet<>();
        String[] hostAndPorts = serverAddrs.split(",");
        for (String hostAndPort : hostAndPorts) {
            String[] hostOrPort = hostAndPort.split(":");
            nodes.add(new HostAndPort(hostOrPort[0].trim(), Integer.parseInt(hostOrPort[1].trim())));
        }
        return nodes;
    }

    /**
     * 预热redis
     */
    public static void preheatRedis() {
        String pingResult = jedisCluster.echo("PING");
        logger.info("PING:{}", pingResult);
    }

    /**
     * 获取Jedis实例
     *
     * @return
     */
    public static JedisCluster getJedisCluster() {
        if (jedisCluster == null) {
            init();
        }
        return jedisCluster;
    }

    /**
     * 释放jedis资源
     */
    public static void close() {
        if (jedisCluster != null) {
            jedisCluster.close();
        }
    }

    public static void main(String[] args) {

    }
}
