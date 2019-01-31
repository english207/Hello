package hzf.demo.file;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by WTO on 2018/8/3 0003.
 */
public class Test
{
    public static void main(String[] args) {

        try
        {
            RandomAccessFile src = new RandomAccessFile("G:\\data\\1.pdf", "r");

            FileChannel srcChannel = src.getChannel();


            RandomAccessFile tar = new RandomAccessFile("G:\\data\\2.pdf", "rw");

            FileChannel tarChannel = tar.getChannel();

            srcChannel.transferTo(0, srcChannel.size(), tarChannel);


        }
        catch (Exception e) { e.printStackTrace(); }







    }
}
