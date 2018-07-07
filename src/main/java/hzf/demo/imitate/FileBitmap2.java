package hzf.demo.imitate;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;

/**
 * Created by WTO on 2018/7/7 0007.
 *
 */
public class FileBitmap2 extends Container
{
    private short cardinality = 0;

    private MappedByteBuffer mappedByteBuffer;
    public FileBitmap2()
    {
        try
        {
            RandomAccessFile rf = new RandomAccessFile("G:\\data\\data.txt", "rw");
            mappedByteBuffer = rf.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 8);
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void add(short x)
    {
        int unsigned = toIntUnsigned(x);
        int idx = unsigned >> 6;
        long p = readData(idx);
        long nval = p | 1l << (unsigned % 64);
        putData(idx, nval);
        cardinality += (p ^ nval) >>> x;
    }

    @Override
    public void remove(short x) {

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

        FileBitmap2 fileBitmap2 = new FileBitmap2();

        fileBitmap2.add((short) 65536);
        fileBitmap2.add((short) 322);

        System.out.println(fileBitmap2.contain((short) 322));
        System.out.println(fileBitmap2.contain((short) 332));


    }

}
