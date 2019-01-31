package hzf.hash;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by huangzhenfeng on 2019/1/3.
 *
 */
public abstract class Worker
{
    public Worker(String worker, String bucket) throws Exception
    {
        this.worker = worker;
        init(bucket);
    }

    private String worker = null;
    private String curHash = null;

    private void init(String bucket) throws Exception
    {
        HashData hashData = HaUtil.getHashData();
        if (Integer.valueOf(bucket) > Integer.valueOf(hashData.curHash)) // 发现新的组
        {
            System.out.println(worker + " 发现新的组 " + bucket);
            HaUtil.setNewHash(bucket);
            curHash = bucket;
        }
        else
        {
            curHash = hashData.curHash;
        }
        go(bucket, curHash);

        HaUtil.setWorkerCurHash(worker, curHash);

        Timer heartTimer = new Timer();
        Timer rehashTimer = new Timer();
        heartTimer.scheduleAtFixedRate(new SendHeartBeat(), 1000, 3000);
        rehashTimer.scheduleAtFixedRate(new CheckReHash(), 1000, 3000);
    }

    public abstract boolean go(String bucket, String hash) throws Exception;

    // 五秒同步一次，同时写入最新时间
    private void heartbeat()
    {
        HaUtil.setWorker(worker, curHash);
    }

    // 查看是否需要rehash
    private void checkRehash()
    {
        HashData hashData = HaUtil.getHashData();
        if (hashData == null)
        {
            return;
        }

        if ( needHash(hashData) )
        {
            System.out.println(worker + " 需要rehash - " + hashData.newHash);
            boolean rehashSuc = rehash(hashData.newHash);
            if (rehashSuc)
            {
                setRehashDone(hashData.newHash);
            }
        }
    }

    // rehash，当结束
    private boolean rehash(String newHash)
    {
        System.out.println(worker + " newHash - " + newHash);
        for (int i = 0; i < 10; i++)
        {
            try
            {
                System.out.println(worker + " rehash now - " + newHash);
                Thread.sleep(1000);
            }
            catch(Exception e) { e.printStackTrace(); }
        }
        return true;
    }

    // 设置本实例hash结束
    private void setRehashDone(String hash)
    {
        this.curHash = hash;
        HaUtil.setWorkerCurHash(worker, hash);
    }

    // 当外部全部获得最新的hash后做的事情，无非就是把不是当前hash的key删除
    private void updateLocal()
    {
        String updateHash;
        if ((updateHash = HaUtil.isNoticeWorker(worker)) != null)
        {
            System.out.println( worker + " updata local - " + updateHash);
            HaUtil.okNoticeWorker(worker, updateHash);
        }
    }

    private boolean needHash(HashData data)
    {
        return Integer.valueOf(curHash) < Integer.valueOf(data.newHash);
    }

    class SendHeartBeat extends TimerTask
    {
        @Override
        public void run()
        {
            heartbeat();
        }
    }

    class CheckReHash extends TimerTask
    {
        @Override
        public void run()
        {
            checkRehash();
            updateLocal();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            new Worker("worker4", String.valueOf(5))
            {
                @Override
                public boolean go(String bucket, String hash)
                {
                    System.out.println(String.format("load bucket[%s] hash[%s]", bucket, hash));
                    return true;
                }
            };

            Thread.sleep(10000);

//            new Worker("worker2", String.valueOf(3))
//            {
//                @Override
//                public boolean go(String bucket, String hash)
//                {
//                    System.out.println(String.format("load bucket[%s] hash[%s]", bucket, hash));
//                    return true;
//                }
//            };
//
//            Thread.sleep(10000);
//
//            new Worker("worker3", String.valueOf(4))
//            {
//                @Override
//                public boolean go(String bucket, String hash)
//                {
//                    System.out.println(String.format("load bucket[%s] hash[%s]", bucket, hash));
//                    return true;
//                }
//            };
        }
        catch(Exception e) { e.printStackTrace(); }
    }
}
