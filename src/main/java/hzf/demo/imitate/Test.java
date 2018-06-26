package hzf.demo.imitate;

import org.roaringbitmap.ArrayContainer;

import java.util.BitSet;

/**
 * Created by huangzhenfeng on 2018/6/15.
 *
 */
public class Test
{
    public static void main(String[] args) {


        org.roaringbitmap.Container arrayContainer = new ArrayContainer();
        DynScaleBitmapContainer dynScaleBitmapContainer = new DynScaleBitmapContainer();

        org.roaringbitmap.Container arrayContainer2 = new ArrayContainer();
        DynScaleBitmapContainer dynScaleBitmapContainer2 = new DynScaleBitmapContainer();


        BitSet bitSet = new BitSet();
        BitSet bitSet2 = new BitSet();

        for (int i = 2568; i < 8908; i++) {
            arrayContainer.add((short) i);
            dynScaleBitmapContainer.add((short) i);
            bitSet.set(i);
        }


        for (int i = 0; i < 3568; i++) {
            arrayContainer2.add((short) i);
            dynScaleBitmapContainer2.add((short) i);
            bitSet2.set(i);
        }

        org.roaringbitmap.Container arrayContainer3 = arrayContainer.andNot(arrayContainer2);

        DynScaleBitmapContainer dynScaleBitmapContainer3 = (DynScaleBitmapContainer) dynScaleBitmapContainer.andNot(dynScaleBitmapContainer2);

        bitSet.andNot(bitSet2);

        System.out.println(arrayContainer3.getCardinality());
        System.out.println(dynScaleBitmapContainer3.cardinality());

        System.out.println(arrayContainer3.toString());
        System.out.println(dynScaleBitmapContainer3.toString());

        System.out.println(bitSet.toString());

        System.out.println();


    }
}
