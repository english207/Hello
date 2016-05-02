package hzf.remote.utils.socket;

import java.net.ServerSocket;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public class ServerBuilder
{
    private static final int MIN_PORT = 8080;
    private static final int MAX_PORT = 60000;

    public static synchronized ServerSocket createServer()
    {
        ServerSocket server = null;
        for (int port = MIN_PORT; port < MAX_PORT; port++)
        {
            try
            {
                server = new ServerSocket(port);
                break;
            }
            catch (Exception e) { System.out.println(e.getMessage()); }
        }
        return server;
    }
}
