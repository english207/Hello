package hzf.demo.bitmap;

import hzf.demo.javaewah.datastructure.BitSet;
import org.roaringbitmap.RoaringBitmap;

/**
 * Created by WTO on 2018/6/8 0008.
 *
 */
public class Test
{
    public static void main(String[] args)
    {
        RoaringBitmap roaringBitmap = new RoaringBitmap();
        roaringBitmap.add(1);
        roaringBitmap.contains(1);

        BitSet bitSet = new BitSet(10);

        bitSet.set(1);
        bitSet.get(1);

        long[] longs = new long[10];

        byte b = 100;

        longs[0] = b;


        System.out.println(longs[0]);




    }
}
