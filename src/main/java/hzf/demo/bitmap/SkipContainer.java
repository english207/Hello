package hzf.demo.bitmap;


import java.util.Arrays;

/**
 * Created by WTO on 2018/6/8 0008.
 *
 *  代替long
 */
public class SkipContainer extends Container
{
    private byte type = 0;
    private byte[] bytes = null;

    @Override
    public void add(byte i)
    {
        int idx = i / 8;
        if ( type < (1 << idx))
        {
            increaseCapacity(idx + 1);
            type |= (1 << idx);
        }

        bytes[idx] |= 1 << (i % 8);
    }

    private void increaseCapacity(int newCapacity)
    {
        if (bytes == null) {
            bytes = new byte[newCapacity];
        }
        this.bytes = Arrays.copyOf(bytes, newCapacity);
    }

    @Override
    public boolean contain(byte x)
    {
        int idx = x / 8;

        if (type == 0 || ((type & idx) != 0) ) {
            return false;
        }

        return (bytes[idx] & 1 << (x % 8)) != 0;
    }

    public static void main(String[] args) {

        SkipContainer container = new SkipContainer();
        byte s = 20;
        container.add(s);
        s = 30;
        container.add(s);
        System.out.println(container.contain(s));
        s = 31;
        System.out.println(container.contain(s));
    }
}
