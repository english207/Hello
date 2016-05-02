package hzf.remote.socket;


import hzf.remote.role.base.RoleDoSomeThing;
import hzf.remote.utils.socket.CloseSocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public class OtherAskMe
{
    private ServerSocket myself;
    private ThreadPoolExecutor threadPoolExecutor;
    private boolean sleep = true;      // 是否停止掉本进程

    private RoleDoSomeThing doSomeThing;

    public OtherAskMe(ServerSocket myself, RoleDoSomeThing doSomeThing)
    {
        this.myself = myself;
        this.doSomeThing = doSomeThing;
        init();
    }

    public void init()
    {
        try
        {
            threadPoolExecutor = new ThreadPoolExecutor(5, 10, 100 * 365, TimeUnit.DAYS, new LinkedBlockingDeque<Runnable>());
            go();
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public void go()
    {
        Thread listenr = new Thread(new TalkWithOther());
        listenr.start();
    }

    class TalkWithOther implements Runnable
    {
        public void run()
        {
            while (sleep)
            {
                try
                {
                    Socket other = myself.accept();
                    threadPoolExecutor.execute(new WhatHeSay(other));
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            System.out.println("I was allowed to go to bed，bye bye!");
        }
    }

    class WhatHeSay implements Runnable
    {
        private Socket other;
        public WhatHeSay(Socket other)
        {
            this.other = other;
        }

        public void run()
        {
            try
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(other.getInputStream()));
                PrintWriter out = new PrintWriter(other.getOutputStream());

                while (true)
                {
                    String content = in.readLine();
                    System.out.println(content);
                    String feedback = doSomeThing.go(content);

                    out.println(feedback);
                    out.flush();

                    if ("receive".equals(feedback) || "end".equals(feedback))
                    {
                        break;
                    }

                    if ("exit".equals(feedback))
                    {
                        System.exit(0);
                    }

                }
                System.out.println("当前问答结束，" + other.getInetAddress().getHostAddress() + ":" + other.getPort());

                // todo 处理content,并且做出相应的反馈

            }
            catch (Exception e) { e.printStackTrace(); }
            finally
            {
                CloseSocket.go(other);
            }
        }
    }

}
