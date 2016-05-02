package hzf.demo.remote;

import java.rmi.Naming;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public class TestClient
{
    public static void main(String[] args)
    {
        try
        {
            Hello hello = (Hello) Naming.lookup("rmi://192.168.137.1:8080/Hello");
            System.out.println(hello.sayHello("hzf"));
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}
