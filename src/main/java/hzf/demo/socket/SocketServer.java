package hzf.demo.socket;

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
public class SocketServer
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket server = new ServerSocket(8000);
            while (true)
            {
                Socket client = server.accept();
                System.out.println(client.getLocalPort());
                WhatHeSay whatHeSay = new WhatHeSay(client);
                Thread thread = new Thread(whatHeSay);
                thread.start();
            }

//

//            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//            PrintWriter out = new PrintWriter(client.getOutputStream());
//            while (true)
//            {
//                String str = in.readLine();
//
//                System.out.println(str);
//                out.println("has receive....");
//
//                out.flush();
//
//                if (str.equals("end"))
//                {
//                    break;
//                }
//            }
//            client.close();

        }
        catch (Exception e) { e.printStackTrace(); }
    }

    static class WhatHeSay implements Runnable
    {
        private Socket client;
        public WhatHeSay(Socket other)
        {
            this.client = other;
        }

        public void run()
        {
            try
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream());
                while (true)
                {
                    String str = in.readLine();

                    System.out.println(str);
                    out.println("has receive....");

                    out.flush();

                    if (str.equals("end"))
                    {
                        break;
                    }
                }
                client.close();
                // todo 处理content,并且做出相应的反馈

            }
            catch (Exception e) { e.printStackTrace(); }
            finally
            {
                CloseSocket.go(client);
            }
        }
    }
}