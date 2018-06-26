package hzf.demo.imitate;

/**
 * Created by WTO on 2018/6/8 0008.
 *
 */
public abstract class Container implements Iterable<Short>, Cloneable
{
    public abstract void add(final short x);

    public abstract void remove(short x);

    public abstract boolean contain(final short x);

    public abstract int getSizeInBytes();

    public abstract int cardinality();

    public abstract Container and(final Container x);

    public abstract Container or(final Container x);

    public abstract Container andNot(final Container x);

    protected static short highbits(int x) {
        return (short) (x >>> 16);
    }

    protected static int toIntUnsigned(short x) {
        return x & 0xFFFF;
    }
}
