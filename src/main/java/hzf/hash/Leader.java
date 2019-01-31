package hzf.hash;

import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by huangzhenfeng on 2019/1/3.
 *
 *      master要做的事情
 *      1，检测掉线的或者下线的client以及slave
 *
 *      slave 要做的事情
 *      1，检测hash是否需要rehash
 *      2，定时发送心跳，心跳包括当前hash值以及设置存活
 *
 *      client
 *      1，定时加载hash
 *      2，定时发送心跳，心跳包括当前hash值以及设置存活
 *
 */
public class Leader
{
    public Leader()
    {
        HaUtil.setMainHash("3", "3");
        new Timer().scheduleAtFixedRate(new GetLeader(), 1000, 3000);
    }

    // 抢占master
    private void getLeader()
    {
        checkHearBeat();
        leaderDoSomeThing();
    }

    // 检查由client，slave写入的实例集合，发现已下线的
    private void checkHearBeat()
    {
        Set<String> workers = HaUtil.getWorkers();
        for (String woker : workers)
        {
            boolean isDown = HaUtil.isWorkerDown(woker);
            if (isDown)
            {
                System.out.println(String.format("worker [%s] is down ? [%s]", woker, true));
            }
        }

        Set<String> clients = HaUtil.getClients();
        for (String client : clients)
        {
            boolean isDown = HaUtil.isClientDown(client);
            if (isDown)
            {
                System.out.println(String.format("client [%s] is down ? [%s]", client, true));
            }
//            System.out.println(String.format("client [%s] is down ? [%s]", client, isDown));
        }
    }

    /**
     *  查找以及分发任务
     *  设置一个batchId，batchId为时间戳
     */
    private void leaderDoSomeThing()
    {
        HashData hashData = HaUtil.getHashData();
        if (hashData == null)
        {
            return;
        }
        String newHash = hashData.newHash;

        // 当 当前的hash与new的hash值不一致的时候以及
        if ( needHash(hashData) )
        {
            Map<String, String> workers = HaUtil.getAllHash("worker");
            int rehashNum = 0;
            for (Map.Entry<String, String> entry: workers.entrySet())
            {
                if ( newHash.equals(entry.getValue()))
                {
                    rehashNum ++;
                    System.out.println(String.format("worker[%s] is rehash down ? [%s]", entry.getKey(), true));
                }
                else
                {
                    break;
                }
            }

            /**
             *  代表着worker已经完全进行rehash成功，
             *  接下来需要进行通知client更新hash为最新hash值，
             *  设置curHash为最新hash值
             */
            if (rehashNum == workers.size())
            {
                HaUtil.setCurHash(newHash);
            }
        }

        //  1，判断是否有需要通知worker，对应的HaUtil.setCurHash
        //  2，同时判断通知队列里面是否有数据，没有才能放入
        if ( HaUtil.isNeedNoticeWorker() )
        {
            Map<String, String> workers = HaUtil.getAllHash("worker");
            Map<String, String> workersRehashDone = HaUtil.getAllReHashDone();
            int done = 0;
            if (workersRehashDone != null && workersRehashDone.size() > 0)
            {
                for (Map.Entry<String, String> entry: workers.entrySet())
                {
                    String worker = entry.getKey();
                    String hash = entry.getValue();

                    String doneHash = workersRehashDone.get(worker);
                    boolean flag = doneHash != null && newHash.equals(hash);
                    if (flag)
                    {
                        done ++;
                    }
                }

                if (done == workers.size())
                {
                    HaUtil.endNoticeWorker();
                    return;
                }
            }


            /**
             *  需要进行通知client更新hash为最新hash值，
             *  设置curHash为最新hash值
             */
            Map<String, String> clients = HaUtil.getAllHash("client");
            int clientRehashNum = 0;
            for (Map.Entry<String, String> entry: clients.entrySet())
            {
                if ( newHash.equals(entry.getValue()))
                {
                    clientRehashNum ++;
                    System.out.println(String.format("client[%s] is rehash down ? [%s]", entry.getKey(), true));
                }
                else
                {
                    System.out.println(String.format("client[%s] is rehash down ? [%s]", entry.getKey(), false));
                    break;
                }
            }

            /**
             *  代表着client已经完全进行rehash成功，
             *  接下来需要进行通知client更新hash为最新hash值，
             *  设置curHash为最新hash值
             */

            if (clientRehashNum == clients.size())
            {
                for (Map.Entry<String, String> entry: workers.entrySet())
                {
                    HaUtil.noticeWorker(entry.getKey(), newHash);
                }
            }
        }
    }

    private boolean needHash(HashData data)
    {
        return !data.curHash.equals(data.newHash);
    }

    class GetLeader extends TimerTask
    {
        @Override
        public void run()
        {
            getLeader();
        }
    }

    public static void main(String[] args) {

        new Leader();
    }
}
