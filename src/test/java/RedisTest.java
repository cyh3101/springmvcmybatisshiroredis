import com.cyh.core.shiro.cache.JedisManager;
import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by cai on 2017/8/22.
 */
public class RedisTest extends BaseTestController{
    @Test
    public void redisSetTest(){

//        JedisManager jedisManager = new JedisManager();
//        Jedis jedis = jedisManager.getJedis();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setTestOnBorrow(true);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("user01","user01");

        System.out.println("user01:" + jedis.get("user01"));
    }
}
