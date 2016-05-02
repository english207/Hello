package hzf.demo.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public interface IHello extends Remote
{
    public String sayHello(String who) throws RemoteException;
}
