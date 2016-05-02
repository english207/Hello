package hzf.demo.remote;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public class Hello extends UnicastRemoteObject implements IHello
{
    private static final long serialVersionUID = 4602780418352429291L;

    protected Hello() throws RemoteException
    {
        super();
        System.out.println("被访问了1");
    }

    protected Hello(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException
    {
        super(port, csf, ssf);
        System.out.println("被访问了2");
    }

    public String sayHello(String who) throws RemoteException
    {
        System.out.println("someone is " + who);
        return "Hello," + who + ",I'm local !";
    }
}
