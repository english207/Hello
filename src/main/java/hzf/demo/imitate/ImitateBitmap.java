package hzf.demo.imitate;

import org.roaringbitmap.RoaringBitmap;

/**
 * Created by huangzhenfeng on 2018/6/20.
 *
 */
public class ImitateBitmap
{
    private HighContainer highContainer = null;

    public ImitateBitmap()
    {
        this.highContainer = new DynHighContainer();
    }

    public void add(int x)
    {
        highContainer = highContainer.add(x);
    }

    public boolean contain(int x)
    {
        return highContainer.contain(x);
    }


    public static void main(String[] args)
    {
        ImitateBitmap bitmap = new ImitateBitmap();
        RoaringBitmap bitmap2 = new RoaringBitmap();


        try
        {
            Thread.sleep(100);
        }
        catch (Exception e) { e.printStackTrace(); }
        long start = System.nanoTime();

        for (int i = 0; i < 10000000; i++)
        {
            int word = Double.valueOf(Math.random() * Integer.MAX_VALUE - 1).intValue();
            bitmap.add(word);
        }
//
        long end = System.nanoTime() - start;
        System.out.println("time - " + end / 1000 /1000);

//        bitmap.add(1612000527);
//        bitmap.add(1989689356);
    }

}
