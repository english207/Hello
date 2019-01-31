package hzf.hash;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by huangzhenfeng on 2019/1/3.
 *
 */
public class Client
{

    private String curHash = null;

    public Client()
    {
        init();
    }

    /**
     *  将当前实例写入到client集合，并设置自己的增长数，
     *  例如set key 10，接着由master去减1，当为0的时候master可判定当前client已下线
     *
     */
    private void init()
    {
        HashData hashData = HaUtil.getHashData();
        this.curHash = hashData.curHash;
        Timer heartTimer = new Timer();
        heartTimer.scheduleAtFixedRate(new SendHeartBeat(), 1000, 3000);
        heartTimer.scheduleAtFixedRate(new UpdateHash(), 1000, 3000);
    }

    /**
     *  定时更新当前的hash
     *  当当前的hash
     */
    public void updateHash()
    {
        String old = this.curHash;
        HashData hashData = HaUtil.getHashData();
        if (hashData != null && hashData.curHash != null && !this.curHash.equals(hashData.curHash))
        {
            this.curHash = hashData.curHash;
            System.out.println(String.format("updataHash - old[%s] - new[%s] ", old, this.curHash));
        }
    }

    class SendHeartBeat extends TimerTask
    {
        @Override
        public void run()
        {
            heartbeat();
        }
    }

    class UpdateHash extends TimerTask
    {
        @Override
        public void run()
        {
            updateHash();
        }
    }

    // 五秒同步一次，同时写入最新时间
    private void heartbeat()
    {
        HaUtil.setClient("client1", curHash);
    }

    public static void main(String[] args)
    {
        new Client();
    }
}
