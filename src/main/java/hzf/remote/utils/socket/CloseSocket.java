package hzf.remote.utils.socket;

import java.net.Socket;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public class CloseSocket
{
    public static boolean go(Socket socket)
    {
        boolean flag = false;
        if (socket != null)
        {
            try
            {
                socket.close();
                flag = true;
            }
            catch (Exception e) { e.printStackTrace(); }
        }

        return flag;
    }
}
