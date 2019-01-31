package hzf.demo.imitate;

import java.util.Iterator;

/**
 * Created by huangzhenfeng on 2018/7/12.
 *
 *      行程压缩，一个short 前6位表达后面有多个，后面10位代表着起始位置
 *
 */
public class DynRunBitmapContainer extends Container
{
    private short[] array = null;
    private int cardinality = 0;

    @Override
    public Container add(short x)
    {
        if (array == null) {
            array = new short[1];
        }
        int unsigned = toIntUnsigned(x);
        int idx = findIdx(unsigned);

        short p = array[idx];
        if (p == 0)
        {

        }

        return this;
    }

    private int findIdx(int unsigned)
    {
        return 0;
    }

    @Override
    public Container remove(short x) {
        return this;
    }

    @Override
    public boolean contain(short x) {
        return false;
    }

    @Override
    public int cardinality() {
        return 0;
    }

    @Override
    public int getSizeInBytes() {
        return 0;
    }

    @Override
    public Container and(Container x) {
        return null;
    }

    @Override
    public Container or(Container x) {
        return null;
    }

    @Override
    public Container andNot(Container x) {
        return null;
    }

    @Override
    public Iterator<Short> iterator() {
        return null;
    }
}
