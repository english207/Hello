package hzf.demo.hwnd;

import java.nio.ByteBuffer;

/**
 * Created by WTO on 2018/10/31 0031.
 *
 */
public class Test
{

    public static void main(String[] args)
    {

        System.out.println("start");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 2024);

        try
        {
            Thread.sleep(100000);
        }
        catch (Exception e) { e.printStackTrace(); }
        
    }
}
