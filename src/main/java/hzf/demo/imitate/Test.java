package hzf.demo.imitate;

import org.roaringbitmap.*;
import org.roaringbitmap.Container;

import java.util.*;

/**
 * Created by huangzhenfeng on 2018/6/15.
 *
 */
public class Test
{
    public static void main(String[] args) {


        Container arrayContainer = new ArrayContainer();
        DynScaleBitmapContainer dynScaleBitmapContainer = new DynScaleBitmapContainer();

        Container arrayContainer2 = new ArrayContainer();
        DynScaleBitmapContainer dynScaleBitmapContainer2 = new DynScaleBitmapContainer();

        Set<Integer> words = new HashSet<Integer>();
        Set<Integer> words2 = new HashSet<Integer>();
        Set<Integer> exist = new HashSet<Integer>();

        arrayContainer.add((short) 50000);
        dynScaleBitmapContainer.add((short) 50000);
        for (int i = 0; i < 1000; i++)
        {
//            int word = Double.valueOf(Math.random() * 50550).intValue();
            int word = i;
            word = Math.abs(word);
            arrayContainer.add((short) word);
            dynScaleBitmapContainer.add((short) word);

            words.add(word);
        }

        System.out.println("arrayContainer - " + arrayContainer.getSizeInBytes());
        System.out.println("dynScContainer - " + dynScaleBitmapContainer.getSizeInBytes());

        for (int i = 1000; i < 2000; i++)
        {
//            int word = Double.valueOf(Math.random() * 50550).intValue();
            int word = i;
            word = Math.abs(word);
            arrayContainer2.add((short) word);
            dynScaleBitmapContainer2.add((short) word);

            words2.add(word);
            words.add(word);
        }

        Iterator<Short> iterator = arrayContainer2.iterator();

        while (iterator.hasNext())
        {
            Short key = iterator.next();
            if (!dynScaleBitmapContainer2.contain(key.shortValue()))
            {
                System.out.println(key);
            }
        }

        System.out.println("arrayContainer - " + arrayContainer2.getSizeInBytes());
        System.out.println("dynScContainer - " + dynScaleBitmapContainer2.getSizeInBytes());

        arrayContainer = arrayContainer.and(arrayContainer2);
        hzf.demo.imitate.Container container3 = dynScaleBitmapContainer.and(dynScaleBitmapContainer2);

        System.out.println(arrayContainer.toString());
        System.out.println(container3.toString());

        for (Integer word : words)
        {
            try
            {
                arrayContainer = arrayContainer.remove(word.shortValue());
                container3.remove(word.shortValue());
            }
            catch (Exception e)
            {
                container3.remove(word.shortValue());

                e.printStackTrace();
            }
        }

        System.out.println("remove - " + arrayContainer.toString());
        System.out.println("remove - " + container3.toString());

        System.out.println("arrayContainer - " + arrayContainer.getSizeInBytes());
        System.out.println("dynScContainer - " + container3.getSizeInBytes());

        TreeSet set = new TreeSet();
        set.addAll(words);

        System.out.println(set.toString());


        System.out.println(Long.toBinaryString(0xFFFF));
        System.out.println(Long.toBinaryString(0xFFFF).length());

        System.out.println(Long.toBinaryString(-15536));

    }
}
