package hzf.demo.bitmap;


import java.util.Arrays;

/**
 * Created by WTO on 2018/6/8 0008.
 *
 *  代替long
 */
public class SkipContainer2 extends Container
{
    private byte type = 0;
    private byte[] bytes = null;

    @Override
    public void add(byte i)
    {
        int idx = i / 8;
        if ( type < (1 << idx))
        {
            increaseCapacity();
            type |= (1 << idx);
        }
        idx = findIdx(idx);
        bytes[idx] |= 1 << (i % 8);
    }

    private void increaseCapacity()
    {
        if (bytes == null) {
            bytes = new byte[1];
        }
        this.bytes = Arrays.copyOf(bytes, bytes.length + 1);
    }

    private int findIdx(int idx)
    {
        int typeTmp = type;
        int idxResult = 0;
        for (int i = 0; i < idx; i++) {
            idxResult += (typeTmp & 1 << i) != 0 ? 1 : 0;
        }
        return idxResult;
    }

    @Override
    public boolean contain(byte x)
    {
        int idx = x / 8;

        if (type == 0 || ((type & idx) != 0) ) {
            return false;
        }
        idx = findIdx(idx);

        return (bytes[idx] & 1 << (x % 8)) != 0;
    }

    public static void main(String[] args) {

        SkipContainer2 container = new SkipContainer2();
        byte s = 20;
        container.add(s);
        s = 30;
        container.add(s);
        System.out.println(container.contain(s));
        s = 31;
        container.add(s);
        s = 21;
        container.add(s);
        System.out.println(container.contain(s));
        s = 22;
        System.out.println(container.contain(s));
    }
}
