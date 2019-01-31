package hzf.demo.imitate;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by hzf on 2018/7/7 0007.
 *
 */
public class FileBitmapContainer extends Container
{
    private int cardinality = 0;

    private MappedByteBuffer mappedByteBuffer;
    public FileBitmapContainer()
    {
        try
        {
            RandomAccessFile rf = new RandomAccessFile("F:\\data\\" + UUID.randomUUID().toString(), "rw");
            mappedByteBuffer = rf.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 8);
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public Container add(short x)
    {
        int unsigned = toIntUnsigned(x);
        int idx = unsigned >> 6;
        long p = readData(idx);
        long nval = p | 1l << (unsigned % 64);
        putData(idx, nval);
        cardinality += (p ^ nval) >>> x;

        return this;
    }

    @Override
    public Container remove(short x) {
        return this;
    }

    @Override
    public boolean contain(short x)
    {
        int unsigned = toIntUnsigned(x);
        int idx = unsigned >> 6;
        long data = readData(idx);
        return (data & 1l << (unsigned % 64)) != 0;
    }

    @Override
    public int getSizeInBytes() {
        return 0;
    }

    @Override
    public int cardinality() {
        return cardinality;
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


    public long readData(int idx)
    {
        return readData(idx, mappedByteBuffer);
    }

    private long readData(int idx, MappedByteBuffer _mappedByteBuffer)
    {
        return  _mappedByteBuffer.getLong(idx * 8);
    }

    private void putData(int idx, long data)
    {
        putData(idx, data, mappedByteBuffer);
    }

    private void putData(int idx, long data, MappedByteBuffer _mappedByteBuffer)
    {
        _mappedByteBuffer.putLong(idx * 8, data);
    }

    public static void main(String[] args) {

        try
        {
            RandomAccessFile rf = new RandomAccessFile("F:\\data\\data.txt", "rw");
            MappedByteBuffer mappedByteBuffer = rf.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, (1024 + 32) * 8);












        }
        catch (Exception e) { e.printStackTrace(); }


    }

}