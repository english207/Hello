package hzf.demo.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public class SocketClient
{
    public static void main(String[] args) throws Exception
    {
        // 从10086 - 65XXX 里获取一个未被占用的端接口asd
        Socket server = new Socket("127.0.0.1", 8080);

        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter out = new PrintWriter(server.getOutputStream());

        BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            String str = wt.readLine();
            out.println(str);
            out.flush();
            if (str.equals("end"))
            {
                break;
            }
            try
            {
                System.out.println(in.readLine());

            }
            catch (Exception e) { e.printStackTrace(); }
        }
        server.close();
    }
}
