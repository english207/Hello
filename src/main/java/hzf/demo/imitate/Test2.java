package hzf.demo.imitate;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by huangzhenfeng on 2018/7/12.
 *
 */
public class Test2
{
    public static void go()
    {
        DynScaleBitmapContainer dynScale = new DynScaleBitmapContainer();
        FileBitmapContainer directByte = new FileBitmapContainer();

        Set<Integer> sets = new HashSet<Integer>();

        for (int i = 0; i < 60000; i++)
        {
            int word = Double.valueOf(Math.random() * 50550).intValue();
            sets.add(word);
        }

        long start = System.nanoTime();
        for (Integer integer : sets)
        {
            dynScale.add(integer.shortValue());
        }
        System.out.println((System.nanoTime() - start) / 1000000 + " - ms");

        start = System.nanoTime();
        for (Integer integer : sets)
        {
            directByte.add(integer.shortValue());
        }
        System.out.println((System.nanoTime() - start) / 1000000 + " - ms");

        for (Integer integer : sets)
        {
            boolean flag1 = dynScale.contain(integer.shortValue());
            boolean flag2 = directByte.contain(integer.shortValue());

            if ( !(flag1 && flag2) )
            {
                System.out.println(flag1);
                System.out.println(flag2);
            }

        }

    }


    public static void main(String[] args)
    {
        Test2.go();
    }



}
