package hzf.demo.remote;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public class TestServer
{
    public static void main(String[] args)
    {
        try
        {
            LocateRegistry.createRegistry(8080);
            Hello hello = new Hello();
            Naming.bind("//192.168.137.1:8080/Hello", hello);
//            Naming.bind("/219.143.144.13/8080:Hello", hello);
            System.out.println("server start……");
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}
