package me.shimanqiang.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * https://blog.csdn.net/zhoubinbin111111/article/details/52370706
 * https://www.cnblogs.com/xiaotong1223/p/9263517.html
 *
 * @author shimanqiang
 * @since 2019/7/11 15:04
 */
public class RedisPoolUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisPoolUtils.class);

    //Redis服务器IP
    private static String ADDR = "10.48.181.94";
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


    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    static {
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
            jedisPool = new JedisPool(config, ADDR, PORT);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 预热redis
     */
    private static void preheatRedis() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //具体的命令
            String pingResult = jedis.ping();
            logger.info("PING:{}", pingResult);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            //注意这里不是关闭连接，在JedisPool模式下，Jedis会被归还给资源池。
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取Jedis实例
     *
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis jedis = jedisPool.getResource();
                return jedis;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static void main(String[] args) {
        Jedis jedis = RedisPoolUtils.getJedis();

        String ping = jedis.ping();
        System.out.println(ping);

        jedis.close();
    }
}
