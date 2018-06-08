package hzf.demo.bitmap;

/**
 * Created by WTO on 2018/6/8 0008.
 *
 */
public abstract class Container
{
    public abstract void add(final byte x);
    public abstract boolean contain(final byte x);

    protected static short highbits(int x) {
        return (short) (x >>> 16);
    }

    protected static int toIntUnsigned(short x) {
        return x & 0xFFFF;
    }
}
