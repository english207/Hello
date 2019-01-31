package hzf.hash;

//import com.wangyin.r2m.client.jedis.JedisPoolConfig;
//import com.wangyin.rediscluster.client.CacheClusterClient;
//import com.wangyin.rediscluster.client.OriginalCacheClusterClient;
//import com.wangyin.rediscluster.provider.IProvider;
//import com.wangyin.rediscluster.provider.ZkProvider;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by huangzhenfeng on 2019/1/3.
 *
 */
public class HaUtil
{
//    private static CacheClusterClient redis = null;
    private static Jedis redis = null;
    static
    {
//        redis = init("deviceBM-temp");
    }

    public static HashData getHashData()
    {
        return getHashData("main", "master");
    }

    private static final String MAIN_HASHDATA_LOCK = "main_hashdata_lock";
    public static void setNewHash(String newHash)
    {
        while (true)
        {
            SetParams params = new SetParams();
            params.nx();
            params.ex(5);
            String ok = redis.set(MAIN_HASHDATA_LOCK, "1", params);
            if ("OK".equals(ok))
            {
                HashData hashData = getHashData();
                if (Integer.valueOf(newHash) > Integer.valueOf(hashData.newHash))
                {
                    System.out.println(redis.hset("main_hashdata_master", "newHash", newHash));
                }
                redis.del(MAIN_HASHDATA_LOCK);
                break;
            }

            try
            {
                Thread.sleep(100);
            }
            catch(Exception e) { e.printStackTrace(); }
        }
    }

    public static void setCurHash(String curHash)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("curHash", curHash);
        map.put("worker_notice_need", "1");
        redis.hmset("main_hashdata_master", map);
    }

    public static void setMainHash(String curHash, String newHash)
    {
        redis.hset("main_hashdata_master", "curHash", curHash);
        redis.hset("main_hashdata_master", "newHash", newHash);
    }

    public static Set<String> getWorkers()
    {
        return getAll("worker");
    }

    public static Set<String> getClients()
    {
        return getAll("client");
    }

    private static Set<String> getAll(String key)
    {
        return redis.smembers(String.format("hash_rebalance_%ss", key));
    }

    public static boolean setWorker(String worker, String curHash)
    {
        return setStatus("worker", worker, curHash);
    }

    public static boolean setClient(String client, String curHash)
    {
        return setStatus("client", client, curHash);
    }

    private static boolean setStatus(String type, String key, String curHash)
    {
        // System.out.println(String.format("执行心跳 type[%s] key[%s]", type, key));
        try
        {
            redis.sadd(String.format("hash_rebalance_%ss", type), key);
            redis.hset(String.format("%s_rebalance_hash", type), key, curHash);
            return redis.set(String.format("%s_status_%s", type, key), 5 + "") != null;
        }
        catch (Exception e) { return false; }
    }

    public static boolean isWorkerDown(String key)
    {
        return isDown("worker", key);
    }

    public static boolean isClientDown(String key)
    {
        return isDown("client", key);
    }

    private static boolean isDown(String type, String key)
    {
        String incr = String.format("%s_status_%s", type, key);
        try
        {
            long result = redis.decr(incr);
            if (result == 0)
            {
                redis.expire(incr, 10);
                redis.srem(String.format("hash_rebalance_%ss", type), key);
                redis.hdel(String.format("%s_rebalance_hash", type), key);
                redis.hdel("worker_rebalance_notice", key);
                return true;
            }
        }
        catch(Exception e)
        {
            System.out.println("error " + incr);
            e.printStackTrace();
        }
        return false;
    }

    public static void setWorkerCurHash(String key, String curHash)
    {
        String type_tmp = String.format("worker_hashdata_%s", key);
        redis.hset(type_tmp, "curHash", curHash);
    }

    public static void setWorkerNewHash(String key, String newHash)
    {
        String type_tmp = String.format("worker_hashdata_%s", key);
        redis.hset(type_tmp, "newHash", newHash);
    }

    public static void setWorkerData(String key, String curHash, String newHash)
    {
        setHashData("worker", key, curHash, newHash);
    }

    public static void setClientData(String key, String curHash, String newHash)
    {
        setHashData("client", key, curHash, newHash);
    }

    private static void setHashData(String type, String key, String curHash, String newHash)
    {
        String type_tmp = String.format("%s_hashdata_%s", type, key);
        redis.hset(type_tmp, "curHash", curHash);
        redis.hset(type_tmp, "newHash", newHash);
    }

    public static HashData getWorkerHashData( String key)
    {
        return getHashData("worker", key);
    }

    public static HashData getClientHashData(String key)
    {
        return getHashData("client", key);
    }

    private static HashData getHashData(String type, String key)
    {
        String type_tmp = String.format("%s_hashdata_%s", type, key);
        Map<String, String> map = redis.hgetAll(type_tmp);
        if (map == null || map.size() == 0)
        {
            return null;
        }

        HashData hashData = new HashData();
        hashData.curHash = map.get("curHash");
        hashData.newHash = map.get("newHash");
        return hashData;
    }

    public static Map<String, String> getAllHash(String type)
    {
        return redis.hgetAll(String.format("%s_rebalance_hash", type));
    }

    public static boolean isNeedNoticeWorker()
    {
        return redis.hexists("main_hashdata_master", "worker_notice_need");
    }

    public static boolean endNoticeWorker()
    {
        redis.del("worker_rebalance_rehash_done");
        return redis.hdel("main_hashdata_master", "worker_notice_need") > 0;
    }

    public static boolean noticeWorker(String worker, String hash)
    {
        return redis.hset("worker_rebalance_notice", worker, hash) > 0;
    }

    public static String isNoticeWorker(String worker)
    {
        return redis.hget("worker_rebalance_notice", worker);
    }

    public static boolean okNoticeWorker(String worker, String hash)
    {
        redis.hset("worker_rebalance_rehash_done", worker, hash);
        return redis.hdel("worker_rebalance_notice", worker) > 0;
    }

    public static Map<String, String> getAllReHashDone()
    {
        return redis.hgetAll("worker_rebalance_rehash_done");
    }

//    private static CacheClusterClient init(String name)
//    {
//        IProvider zkProvider = new ZkProvider(name, "172.25.46.201:2181,172.25.46.221:2181,172.25.46.241:2181", 25000, 5000);
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(50);
//        jedisPoolConfig.setMaxWaitMillis(10000);
//        return new OriginalCacheClusterClient(zkProvider, 2000, 5, jedisPoolConfig);
//    }

    public static void main(String[] args)
    {

    }
}
