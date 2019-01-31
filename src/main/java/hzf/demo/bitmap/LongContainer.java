package hzf.demo.bitmap;


import java.util.Arrays;

/**
 * Created by WTO on 2018/6/8 0008.
 *
 *  代替long
 */
public class LongContainer extends Container
{
    private long data = 0;
    public LongContainer() {
    }

    public LongContainer(byte type, byte[] bytes)
    {
        if (bytes != null)
        {
            for (int i = 0; i < bytes.length; i++)
            {
                byte b = bytes[0];
                long tmp = b << i * 8;
                data |= tmp;
            }
        }
    }

    @Override
    public Container add(short i)
    {
        data = data | (1L << i );
        return this;
    }

    @Override
    public boolean contain(short x)
    {
        return (data & 1 << x) != 0;
    }

    public static void main(String[] args) {
        LongContainer container = new LongContainer();
        byte b = 40;
        container.add(b);
        System.out.println(container.contain(b));
        b = 20;
        container.add(b);
        System.out.println(container.contain(b));
        b = 4;
        container.add(b);
        System.out.println(container.contain(b));
        b = 42;
        System.out.println(container.contain(b));

    }
}
