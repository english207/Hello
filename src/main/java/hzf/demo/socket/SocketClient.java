package hzf.demo.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public class SocketClient
{
    public static void main(String[] args) throws Exception
    {
        // 从10086 - 65XXX 里获取一个未被占用的端接口asd
        Socket server = new Socket("192.168.1.30", 8000);


//        Socket server = new Socket("192.168.44.128", 11332);
//        Socket server = new Socket("192.168.1.30", 11332);
//        Socket server = new Socket("127.0.0.1", 11332);

        int[] a = {5,2,4,3,6,78,90,110};

        PrintWriter out = new PrintWriter(server.getOutputStream());

        BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            String str = wt.readLine();
            out.println("1000,1000,1000,1000,1300,1900,1950,2000");
            out.flush();
            if (str.equals("end"))
            {
                break;
            }
        }
        server.close();


    }
}
