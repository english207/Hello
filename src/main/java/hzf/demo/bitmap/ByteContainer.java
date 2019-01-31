package hzf.demo.bitmap;


import java.util.Arrays;

/**
 * Created by WTO on 2018/6/8 0008.
 *
 *  代替long
 */
public class ByteContainer extends Container
{
    private byte type = 0;
    private byte[] bytes = null;

    @Override
    public Container add(short i)
    {
        int idx = i / 8;
        if (idx == 7)
        {
            LongContainer container = toLongContainer();
            return container.add(i);
        }

        int idxTmp = (1 << idx) & 0xFFFF;
        if (type < idxTmp)
        {
            expandTo(idx);
            type |= idxTmp;
        }

        bytes[idx] |= 1 << (i % 8);
        return this;
    }

    private void expandTo(int idx)
    {
        if (bytes == null) {
            bytes = new byte[idx + 1];
        }
        this.bytes = Arrays.copyOf(bytes, idx + 1);
    }

    @Override
    public boolean contain(short x)
    {
        int idx = x / 8;
        return (bytes[idx] & 1 << (x % 8)) != 0;
    }

    private LongContainer toLongContainer()
    {
        return new LongContainer(type, bytes);
    }

    public static void main(String[] args) {
        ByteContainer container = new ByteContainer();
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
        b = 63;
        Container container1 = container.add(b);
        b = 20;
        System.out.println(container1.contain(b));

    }
}
