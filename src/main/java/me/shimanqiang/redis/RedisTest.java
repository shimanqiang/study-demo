package me.shimanqiang.redis;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.Objects;

/**
 * http://www.redis.cn/documentation.html
 * https://blog.csdn.net/qq315737546/article/details/79728676
 *
 * @author smq
 * @since 2019/7/11 14:57
 */
public class RedisTest {

    public static void main(String[] args) throws Exception {
        RedisClusterUtils.preheatRedis();

        JedisCluster jedisCluster = RedisClusterUtils.getJedisCluster();

        test001_set(jedisCluster);
        test002_setnx_px(jedisCluster);
        test003_unlock_lua(jedisCluster);

        RedisClusterUtils.close();
    }

    private static void test003_unlock_lua(JedisCluster jedisCluster) {
        Long UNLOCK_SUCCESS = 1L;
        String fullKey = "abc";
        String value = "123";
        jedisCluster.set(fullKey, value);
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedisCluster.eval(script, Collections.singletonList(fullKey), Collections.singletonList(value));
        System.out.println(result);
        System.out.println(result.getClass());
        if (Objects.equals(UNLOCK_SUCCESS, result)) {
            System.out.println("234567");
        }
        String abc = jedisCluster.get(fullKey);
        System.out.println(abc);
    }

    private static void test002_setnx_px(JedisCluster jedisCluster) throws InterruptedException {
        /**
         * 从 Redis 2.6.12 版本开始， SET 命令的行为可以通过一系列参数来修改：

         EX second ：设置键的过期时间为 second 秒。 SET key value EX second 效果等同于 SETEX key second value 。
         PX millisecond ：设置键的过期时间为 millisecond 毫秒。 SET key value PX millisecond 效果等同于 PSETEX key millisecond value 。
         NX ：只在键不存在时，才对键进行设置操作。 SET key value NX 效果等同于 SETNX key value 。
         XX ：只在键已经存在时，才对键进行设置操作。
         */
        SetParams params = new SetParams();
        params.nx();
        params.px(5 * 1000);
        jedisCluster.set("abc", "123", params);

        int tryTimes = 20;
        while (tryTimes > 0) {
            tryTimes--;
            if (tryTimes > 10) {
                jedisCluster.set("abc", "456", params);
            }
            String abc = jedisCluster.get("abc");
            System.out.println(abc);
            Thread.sleep(1000);
        }
    }

    private static void test001_set(JedisCluster jedisCluster) {
        String abc1 = jedisCluster.set("abc", "123");
        System.out.println(abc1);
        Long abc2 = jedisCluster.setnx("abc", "456");
        System.out.println(abc2);
        String abc = jedisCluster.get("abc");
        System.out.println(abc);
        jedisCluster.del("abc");
    }
}
