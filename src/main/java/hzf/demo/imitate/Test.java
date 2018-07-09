package hzf.demo.imitate;

import org.roaringbitmap.ArrayContainer;

import java.util.BitSet;

/**
 * Created by hzf on 2018/6/15.
 *
 */
public class Test
{
    public static void main(String[] args) {

        org.roaringbitmap.Container arrayContainer = new ArrayContainer();
        org.roaringbitmap.Container arrayContainer2 = new ArrayContainer();

        BitSet bitSet = new BitSet();
        BitSet bitSet2 = new BitSet();

        for (int i = 2568; i < 8908; i++) {
            arrayContainer2.add((short) i);
            bitSet2.set(i);
        }

        for (int i = 0; i < 3568; i++) {
            arrayContainer.add((short) i);
            bitSet.set(i);
        }

        org.roaringbitmap.Container arrayContainer3 = arrayContainer.or(arrayContainer2);
        bitSet.or(bitSet2);

        System.out.println(arrayContainer3.toString());
        System.out.println(bitSet.toString());

        System.out.println(arrayContainer3.getCardinality());
        System.out.println(bitSet.cardinality());


    }
}
